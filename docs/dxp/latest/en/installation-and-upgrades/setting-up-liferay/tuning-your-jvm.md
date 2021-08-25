# Tuning Your JVM

Java Virtual Machine (JVM) tuning primarily focuses on adjusting Java heap and non-heap settings and configuring garbage collection. Finding settings that perform well for you depend on your system's load and your hardware. The settings discussed here can be used as a starting point for tuning your JVM. 

You can adapt the example Oracle JVM settings to settings for your JVM. Please consult [the compatibility matrix](https://help.liferay.com/hc/en-us/articles/360049238151) for compatible JVMs.

## Set Heap and Non-Heap Space

The JVM's memory comprises heap and non-heap spaces. The heap contains a space for young generation objects and a space for old generation objects. Static content and just-in-time (JIT) compiled Java code are stored in non-heap native space. Here's an example configuration.

**Memory Settings Example**

``` 
-Xms2560m -Xmx2560m 
-XX:NewSize=1536m -XX:MaxNewSize=1536m 
-XX:MetaspaceSize=768m -XX:MaxMetaspaceSize=768m 
-XX:InitialCodeCacheSize=64m -XX:ReservedCodeCacheSize=96m 
```

**Memory Settings Explained**

| Memory Setting | Explanation |
| :------ | :---------- |
| `-Xms2560m` | Initial space for heap. |
| `-Xmx2560m` | Maximum space for heap. |
| `-XX:NewSize=1536m`| Initial new space. Setting the new size to half of the total heap typically provides better performance than using a smaller new size. |
| `-XX:MaxNewSize=1536m` | Maximum new space. |
| `-XX:MetaspaceSize=768m` | Initial space for static content. |
| `-XX:MaxMetaspaceSize=768m` | Maximum space for static content. |
| `-XX:InitialCodeCacheSize=64m` | Initial space for JIT-compiled code. Too small a code cache (`48m` is the default) reduces performance, as the JIT isn't able to optimize high frequency methods. |
| `-XX:ReservedCodeCacheSize=96m` | Maximum space for JIT-compiled code. |

```{warning}
Avoid allocating more than 32g to your JVM heap. Your heap size should be commensurate with the speed and quantity of available CPU resources.
```

## Set Survivor Space

In the old generation space (in the heap), large garbage collections can cause noticeable slowdowns. Avoid this by allowing more objects to stay longer in the *survivor space* before promoting them to the old generation space. The survivor space holds young generation objects that survive Eden garbage collection. Here are initial survivor space parameters to try.

**Survivor Settings Example**

```
-XX:SurvivorRatio=16 -XX:TargetSurvivorRatio=50 -XX:MaxTenuringThreshold=15
```

**Survivor Settings Explained**

| Survivor Setting | Explanation |
| :------ | :---------- |
| `-XX:SurvivorRatio=16` | Makes the survivor space 1/16 of the new space (the initial new space is `1536m`). |
| `-XX:TargetSurvivorRatio=50` | Instructs the JVM to use 50% of the survivor space after each Eden garbage collection. |
| `-XX:MaxTenuringThreshold=15` | Keeps survivors in the survivor space for up to 15 garbage collections before promotion to the old generation space. |

## Configure Garbage Collection

Choosing appropriate garbage collector (GC) algorithms helps improve Liferay instance responsiveness.

### Garbage Collection on Java 8

Start tuning using parallel throughput collectors in the new generation (ParNew) and concurrent mark sweep (CMS) low pause collectors in the old generation.

**GC Settings Example**

```
-XX:+UseParNewGC -XX:ParallelGCThreads=16
-XX:+UseConcMarkSweepGC
-XX:+CMSParallelRemarkEnabled -XX:+CMSCompactWhenClearAllSoftRefs
-XX:CMSInitiatingOccupancyFraction=85 -XX:+CMSScavengeBeforeRemark
```

**GC Settings Explained**

| GC Setting | Explanation |
| :--------- | :---------- |
| `-XX:+UseParNewGC` | Enables parallel collectors for the new generation. |
| `-XX:ParallelGCThreads=16` | Allocates 16 threads for parallel garbage collection. Set the number of threads based on the CPU threads available, which you can get on Linux by running `cat /proc/cpuinfo`. The threads use memory from the old generation space. |
| `-XX:+UseConcMarkSweepGC` | Enables the concurrent mark sweep GC algorithm for the old generation. |
| `-XX:+CMSParallelRemarkEnabled` | Enables remarking during program execution. |
| `-XX:+CMSCompactWhenClearAllSoftRefs` | Move memory blocks closer together when using CMS with the `ClearAllSoftRefs` setting. |
| `-XX:CMSInitiatingOccupancyFraction=85` | Initiates CMS when this percent of old generation space is occupied. |
| `-XX:+CMSScavengeBeforeRemark` | Execute Eden GCs before re-marking objects of CMS. |

```{note}
There are additional "new" algorithms like Garbage-First (G1), but Liferay Engineering's tests for G1 indicated that it does not improve performance. Since your application performance may vary, you should add G1 to your testing and tuning plans.
```

### Garbage Collection on Java 11

Since CMS and ParNew algorithms are deprecated in Java 11, use the Garbage-First (G1) algorithm. It's enabled by default. Start testing with G1's default settings.

## Consider Using Large Pages 

On systems that require large heap sizes (e.g., above 4GB), it may be beneficial to use large page sizes.

### Configure Large Pages on Your Machine

Here's how to configure large pages (aka "huge pages") on Linux:

1. Determine the number of pages to use based on your hardware specification and application profile. On Linux, report your page size by executing this command:

    ```bash
	cat /proc/meminfo | grep Hugepagesize
	```

    Result:

    ```properties
	Hugepagesize = 2048 kB
	```

1. Set the number of pages to enable. On Linux, edit your `/etc/sysctl.conf` file and set `vm.nr_hugepages` to the number of pages. For example,

	```properties
	vm.nr_hugepages = 10
	```
1. Enable the pages. On Linux, execute this:

    ```bash
	sysctl -p
	```

1. Restart your machine.

### Configure Large Pages in Your JVM 

Here's how to configure your JVM to use large pages:

**Large Page Settings Example**

```
-XX:+UseLargePages -XX:LargePageSizeInBytes=256m
```

**Large Page Settings Explained**

| Large Page Setting | Explanation |
| :------ | :---------- |
| `-XX:+UseLargePages` | Enables large pages. |
| `-XX:LargePageSizeInBytes=256m` | Make sure the total large page size (from `cat /proc/meminfo`, calculate `HugePages_Total * Hugepagesize`) can contain all of your JVM's memory usage. |

Adjust page sizes based on your hardware specification and application profile.

## Conclusion 

Now that you're familiar with the common JVM options and example configurations, start experimenting with them in your testing environment. Monitor the garbage collection statistics to ensure your environment has sufficient memory allocations. Tune your settings to minimize garbage collection effects on performance and maximize processing speed. With proper testing and tuning, you'll optimize the JVM for your Liferay instance.
