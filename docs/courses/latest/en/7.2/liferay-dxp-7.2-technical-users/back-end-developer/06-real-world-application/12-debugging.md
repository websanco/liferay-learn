---
description: 06 - Real World Application
title: Debugging
order: 12
---

## Debugging

Your application might be failing integration tests or crashing in production because of unknown reasons. The code complexity might have gotten to the point where you aren't sure how everything is working together. Developers make mistakes, and tests can't cover every possible use case scenario. This is where debugging comes in. Debugging covers a number of different approaches, tools, and methodologies under its umbrella.

Here, for the sake of training, we limit the scope to the basics of using the IDE debugger tool, which enables you to monitor and control the execution of a program, setting breakpoints and changing values in memory. We'll also briefly discuss some methods of troubleshooting and debugging issues in production, where there's no source code or IDE available. 

#### Introducing JPDA

JPDA (Java Platform Debugger Architecture) is the backbone of Java debugging. It's a collection of Java APIs to facilitate debugging, profiling, and monitoring local and remote Java applications.

JPDA consists of three layers: JDI, JDWP, and JVM TI. 

* __JDI__ (Java Debug Interface) is the user interface definition layer. In our training context, the implementation is Eclipse-based Dev Studio.

* __JDWP__ (Java Debug Wire Protocol) defines the communication between the debuggee and the debugger. 

* __JVM TI__ (Java VM Tool Interface) API defines the debugging services a VM provides and a native programming interface for use by debugging, monitoring, and profiling tools. It provides a way to inspect the state of running JVM and to modify and control execution of applications running in the JVM. JVM TI provides an event-based support for bytecode instrumentation, the ability to alter the Java virtual machine bytecode instructions. JVM TI clients are called *agents*.

While JVM TI is a native programming interface requiring agents to be written in C / C++, `java.lang.instrument` is a higher-level API on top of JVM TI, providing a Java programming interface for bytecode instrumentation, which is one of the most important enablers of Java debugging.

#### Enabling Debug Mode in IDE

To enable debugging, the JVM has to be started in debug mode with JPDA enabled. If you are using a Tomcat server adapter in Liferay Dev Studio, IDE takes care of enabling JPDA, and you only need to start the server in debug mode.

<img src="../images/starting-debug.png" style="max-height:100%"/>

Tomcat can also be started manually in debug mode by simply adding the "jpda" startup option to catalina script:

```bash
TOMCAT_HOME/catalina.sh jpda start
```

Generally, servlet containers and Java EE servers can be started in debug mode by adding appropriate Xagentlib options for the JVM:

```xml
-Xagentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=8000
```

When debugging a remote server (including a server not launched from IDE), a remote debugging connection profile, corresponding to the JDWP options of JVM, has to be added in the IDE:

<img src="../images/setup-remote-debugging.png" style="max-height:30%"/>

#### Debugging Basics

In running debugger, we are often interested to see why a certain variable gets an unprecedented value and what the application state is when some code block, method, or call is reached. For that purpose, we set *breakpoints*, which can be static, meaning that we always stop the execution at that point, or conditional, meaning that the execution only stops when a certain condition is met. We can also set variable watchpoints, which are triggered when a variable value is changed.

<img src="../images/adding-breakpoint.png" style="max-height:100%"/>

At the breakpoint, we can not only watch but also change variable values, for example, to check if the issues we were resolving are dependent on that specific value. Double-clicking the value allows you to change it.

<img src="../images/watch-variable-values.png" style="max-height:30%"/>

When we have an idea about the area where the issue comes from, we can set a breakpoint and use debugger stepping functionalities to execute the code line by line forward, step into a method call, or even go backwards in the execution:

<img src="../images/use-steps.png" style="max-height:100%"/>

#### Setting Up Liferay Source Code for Debugging

Sometimes you might want to put a breakpoint in Liferay's code to see what happens at some specific point. For that purpose, you have to set up the portal source code for the IDE. Liferay provides source code packages for all the portal releases, including fix packs. To set up the source code, you need to:

1. Download and extract the source code package
1. Import the source code project into a workspace
1. Set up the debugger configuration (if remote)	

#### Step 1 - Download the Source Code

Liferay commercial release source codes can be downloaded from the customer portal at https://customer.liferay.com/.

#### Step 2 - Import the Source Code into the Workspace (Eclipse)

When you extract the source code package and try to import that into Eclipse, you might notice there are no Eclipse project files present. To import the code into your workspace and auto-create the project files, use *File → Open Projects* from the File System:

<img src="../images/import-source-code.png" style="max-height:100%"/>

#### Step 3 - Set Up Debugger Configuration (Remote Debugging)

After the source code project is set up in the workspace, you need to create a debugger configuration for it:

<img src="../images/setup-remote-debugging-for-portal.png" style="max-height:40%"/>

Now you can launch the debugging session profile. Before launching, you should check one more thing. When you alter the portal source code or launch the debugging profile, the IDE tries to build the project automatically by default, which might fail or just take a lot of time. Disable automatic project-building in the project's settings:  

<img src="../images/disable-build-automatically.png" style="max-height:40%"/>

Also disable building at launch in your Eclipse workspace preferences in *Window → Preferences → Run/Debug → Launching*:

<img src="../images/disable-build-at-launch.png" style="max-height:100%"/>

#### Overview of Debugging in Production

Debugging in production usually has a different toolset. At the development stage, the development environment, source code, and IDE debugger are available, and often there's more time to resolve issues. The nature of debugging is forward-tracing, where you can run the program line by line to see what's causing or would possibly be causing an issue. Testing also falls into this category of forward-tracing debugging. 

Debugging issues in production systems is usually backward-tracing and relies on interpreting the issue symptoms from logs, stack traces, and thread dumps. Often, strict time constraints and security policies mean only a limited toolset is available.

Debugging in production often involves monitoring and profiling JVM and applications using, for example, APM tools. Here, we limit the scope of discussion to introducing thread dumps and JVM, getting memory statistics with Oracle JDK tools.

<br />

#### Thread Dumps

A thread dump shows information about what each thread is doing at a given time. This information includes the exact method call being executed and the thread state at that point in time.

Thread dumps are crucial in troubleshooting when the system is having performance issues and freezes. They show which threads and which function calls might be blocking the execution.

To create a thread dump of JVM with JDK tools, you first have to find the process ID of the running JVM. Depending on the operating system, there are multiple ways to get that information, but running the JDK tool [jps](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jps.html) from the _Command Line_ is handy for this purpose:

```bash
jps -v
```

After you've found the process id (PID), you can use the JDK tool [Jstack](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jstack.html) to create a thread dump of running JVM:

```bash
liferay@liferay> jstack -l PID > /opt/tmp/thread_dump.txt
```

The internal mechanics of thread dumps are beyond the scope of discussion here, but the excerpt below shows an example of a thread in the WAITING state:

```bash
...

"liferay/monitoring-1" #174 daemon prio=5 os_prio=0 tid=0x00007f45f1067800 nid=0x2be1 waiting on condition [0x00007f45a31fe000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000000c7bcd0d0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at com.liferay.portal.kernel.concurrent.TaskQueue.take(TaskQueue.java:258)
	at com.liferay.portal.kernel.concurrent.ThreadPoolExecutor._getTask(ThreadPoolExecutor.java:548)
	at com.liferay.portal.kernel.concurrent.ThreadPoolExecutor.access$500(ThreadPoolExecutor.java:37)
	at com.liferay.portal.kernel.concurrent.ThreadPoolExecutor$WorkerTask.run(ThreadPoolExecutor.java:672)
	at java.lang.Thread.run(Thread.java:748)

   Locked ownable synchronizers:
	- None

"LCS Worker 4" #171 prio=5 os_prio=0 tid=0x00007f45e002b800 nid=0x2bde waiting on condition [0x00007f45ac16d000]
   java.lang.Thread.State: WAITING (parking)
...
```

If you had a stability issue in your system, you'd probably start looking for threads in the `BLOCKED` state, stealing the available threads and blocking the program execution, possibly caused by certain method calls. Then you would start to investigate why those calls are blocking the execution.

When debugging issues in production, it's good to remember the time aspect: a thread could, for example, be blocked at some point in time, and that would be completely normal. If that very same thread would be blocked after 30 seconds, you'd probably be having an issue. That's why, when taking thread dumps, it's important to take several thread dumps during the problematic application state.

#### Monitoring Memory with JStat

JVM problems are often memory-related. Your application might be leaking memory resources, or there might just be a resource allocation problem during a peak load. The JDK tool [JStat](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jstat.html) shows statistics of JVM memory usage and garbage collection. It both helps in detecting the memory leaks and in tuning the JVM memory and garbage collection parameters.

The _Command Line_ example below shows the garbage collection and memory statistics with the sample rate of 500ms:

```bash
liferay@liferay-VirtualBox $ jstat -gcutil -t 27284 500ms
Timestamp         S0     S1     E      O      M     CCS    YGC     YGCT    FGC    FGCT     GCT   
       193093.6  24.19   0.00  95.19  57.11  91.63  82.49    450   10.111     8    3.835   13.947
       193094.1  24.19   0.00  95.19  57.11  91.63  82.49    450   10.111     8    3.835   13.947
       193094.6  24.19   0.00  95.19  57.11  91.63  82.49    450   10.111     8    3.835   13.947
       193095.1  24.19   0.00  95.19  57.11  91.63  82.49    450   10.111     8    3.835   13.947
       193095.6  24.19   0.00  95.19  57.11  91.63  82.49    450   10.111     8    3.835   13.947
       193096.1  24.19   0.00  95.19  57.11  91.63  82.49    450   10.111     8    3.835   13.947
       193096.6  24.19   0.00  97.09  57.11  91.63  82.49    450   10.111     8    3.835   13.947
       193097.1  24.19   0.00  98.98  57.11  91.63  82.49    450   10.111     8    3.835   13.947
       193097.6  24.19   0.00  98.98  57.11  91.63  82.49    450   10.111     8    3.835   13.947
       193098.1  24.19   0.00  98.98  57.11  91.63  82.49    450   10.111     8    3.835   13.947
       193098.6  24.19   0.00 100.00  57.11  91.63  82.49    450   10.111     8    3.835   13.947
       193099.1   0.00  95.58   1.89  57.14  91.63  82.49    451   10.125     8    3.835   13.960
       193099.6   0.00  95.58   1.99  57.14  91.63  82.49    451   10.125     8    3.835   13.960
       193100.1   0.00  95.58   1.99  57.14  91.63  82.49    451   10.125     8    3.835   13.960
       193100.6   0.00  95.58   1.99  57.14  91.63  82.49    451   10.125     8    3.835   13.960
       193101.1   0.00  95.58   1.99  57.14  91.63  82.49    451   10.125     8    3.835   13.960
       193101.7   0.00  95.58   1.99  57.14  91.63  82.49    451   10.125     8    3.835   13.960
       193102.2   0.00  95.58   1.99  57.14  91.63  82.49    451   10.125     8    3.835   13.960
```

<div class="summary-chapter">
<h3>Knowledge Check</h3>
<ul>
  <li>Debugging covers a number of _________________________ under its umbrella.</li>
  <li>_________________________ is a collection of Java APIs used to facilitate debugging, profiling, and monitoring local and remote Java applications.</li>
  <li>Debugging issues in production systems is usually _________________________ and often relies on interpreting the symptoms from _________________________, _________________________, and _________________________.</li>
</ul>
</div>