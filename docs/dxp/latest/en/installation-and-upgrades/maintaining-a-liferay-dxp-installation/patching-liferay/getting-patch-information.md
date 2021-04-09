# Getting Patch Information

> Subscribers

You can see patch information at a glance, which helps maintain your installation and get help from Liferay Support. The Patching Tool's `info` command lists the following information:

* Service Pack version
* Patching Tool version
* Patches Installed
* Patches available (in your `patching-tool/patches/` folder)

They clarify what you've installed and what's available to install.

Here's an example execution:

```
patching-tool>./patching-tool.sh info
Loading product and patch information...
Product information:
  * build number: 7310
  * service pack version:
    - available SP version: 1
    - installable SP version: 1
  * patching-tool version: 3.0.5
  * time: 2020-09-01 20:26Z
  * host: 91WRQ72 (8 cores)
  * plugins: no plugins detected

Currently installed patches: dxp-1-7310

Available patches: dxp-2-7310, dxp-3-7310

Detailed patch list:
  [*-] dxp-2-7310 :: Installed; Won't be installed: dxp-3 contains the fixes included in this one :: Built for LIFERAY
  [ I] dxp-3-7310 :: Currently not installed; Will be installed. :: Built for LIFERAY
```

Now you can get installation details using a single command.
