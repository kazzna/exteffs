# exteffs

Extensible Effects for Scala

## Local Development Setup

### GitHub Packages Authentication

This project depends on the [types](htpps://github.com/kazzna/types) library published to GitHub Packages. 
To resolve this dependency locally, you must provide authentication credentials.

#### Prerequisites

Create a Personal Access Token (PAT) on GitHub with at least the `read:packages` scope.

#### Configuration

Create the credentials file at `~/.sbt/1.0/ghpackages.credentials`:

```plaintext
realm=GitHub Package Registry
host=maven.pkg.github.com
user=YOUR_GITHUB_USERNAME
password=YOUR_PERSONAL_ACCESS_TOKEN
```

Restrict the file permissions to prevent unintended access:

```bash
chmod 600 ~/.sbt/1.0/ghpackages.credentials
```

This configuration allows sbt to authenticate with GitHub Packages when resolving the `jp.kazzna:types` dependency. 
No further setup is required for local development.
