# Command Line Gogo Shell

If you're in a development environment, you can interact with the module framework locally from the command line.

```{warning}
Gogo shell should only be run from the command line in development environments. In production environments, run Gogo shell in the Control Panel, as demonstrated in [Using the Gogo Shell](../using-the-gogo-shell.md) .
```

## Prerequisites

Gogo shell on the command line requires that the server is running in Developer Mode. You can enable Developer Mode on the server in [Developer Studio](../../../developing-applications/tooling/developer-studio.md) or by setting the following [Portal Property](../../../installation-and-upgrades/reference/portal-properties.md):

```properties
include-and-override=portal-developer.properties
```

## Executing Commands

You can execute Gogo shell commands using [Blade CLI](../../../developing-applications/tooling/blade-cli/installing-and-updating-blade-cli.md) or a telnet session.

### Using Blade CLI

You can execute individual commands using Blade CLI:

```bash
blade sh [Gogo shell command]
blade sh [another Gogo shell command]
...
```

### Using a Telnet Session

You can execute commands in a `telnet` session:

1. Open a session:

    ```bash
    telnet localhost 11311
    ```

1. Execute Gogo shell commands.

1. When you're done executing commands, execute the `disconnect` command to end the session.

```{warning}
DO NOT execute the following commands. They stop the module framework.

`close`

`exit`

`shutdown`
```

## Conclusion

Now that you know how to run Gogo shell from the command line, explore the available [Gogo shell commands](./gogo-shell-commands.md). If you need to use Gogo shell in a production environment, please see [Using the Gogo Shell](../using-the-gogo-shell.md).