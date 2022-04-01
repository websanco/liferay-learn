## Patching

<div class="ahead">

#### Exercise Goals

* Learn how to use the patching tool

</div>

#### Add a Hotfix to a Liferay Bundle
1. **Unzip** the `patching-tool-3.0.20.zip` file into your Tomcat bundle.
* **Go to** the `patching-tool/patches` directory.
* **Copy** the `liferay-hotfix-550-7310.zip` from your `exercise-src`.
* **Paste** the hotfix into the `patching-tool/patches` directory.
* **Open** _Terminal_ (Mac/Linux) or _Powershell_ (Windows) in the `patching-tool` directory.
* **Type** `./patching-tool.sh info` (Mac/Linux) or `patching-tool info` (Windows).
* **Press** _Enter_ to run the patching tool.

```shell
$ ./patching-tool.sh info
Loading product and patch information...
Product information:
* installation type: binary
* build number: 7310
* service pack version:
    - available SP version: Not available
    - installable SP version: Not available
* patching-tool version: 3.0.20
* time: 2019-07-19 09:17Z
* host: training (64 cores)
* plugins: no plugins detected

```

```shell
Currently installed patches: -
Available patches: hotfix-550-7310

Detailed patch list: 
[ I] hotfix-550-7310 :: Currently not installed; Will be installed. :: DXP-TRAINING :: Built for THESPACEPRO
$ 
```

#### Install the Hotfix
1. **Type** `./patching-tool.sh install` (Mac/Linux) or `patching-tool install` (Windows) in the Terminal.
* **Press** _Enter_.


```shell
$ ./patching-tool.sh install
One patch is ready to be installed. Applying hotfix-11...
Cleaning up: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%..100%]
Installing patches: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%..100%]
Processing bundles: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%..100%]

No module-mapping.txt found in Patching Tool root folder.
For further details, see https://help.liferay.com/hc/en-us/articles/360024518932-Patching-Tool-3-0-20
The installation was successful. One patch is installed on the system.
$
```

<br />

---

#### Bonus Exercises:
1. Install a new patch to your Liferay DXP instance and then run `./patching-tool.sh info` to see the changes made.
