# Bound CI Demo

A multi-module Gradle project demonstrating dynamic versioning, API contract publishing, and real artifact consumption during build pipelines.  
Built to simulate real-world CI environments handling multiple PRs and snapshot collisions.

## 🎯 Goal

- Generate an API using OpenAPI Generator in a separate module.
- Publish the API as a standalone artifact with its own versioning.
- Consume the published API as a dependency instead of compiling it locally.
- Simulate functional testing scenarios by downloading the published API.

## 🐞 Problem

- **Snapshot Overriding Risk**:  
  When multiple engineers work on the same base version (e.g., `2.5.0`), publishing `-SNAPSHOT` artifacts can cause one engineer’s upload to override another's.  
  This results in unpredictable retrievals during testing, where consumers may unknowingly fetch someone else's artifact instead of their own.
- **Environment-Aware Publishing Complexity**:  
  Publishing snapshots and releases to different repositories is a common best practice,  
  but correctly configuring dynamic repository selection based on the environment (e.g., CI branch, tag, or manual trigger) usually requires complex Gradle scripts or CI-specific logic.  
  Setting up multiple publications aware of the environment adds fragility and overhead to the build configuration.
- **Dynamic Version Management**:  
  In a real multi-module project, one module (e.g., an application) often needs to consume another module (e.g., an API) as an artifact built during the same process.  
  Without consistent, dynamic versioning handled inside Gradle, builds are forced to rely on external CI scripts to calculate and inject versions manually, leading to fragile and error-prone pipelines.

## 🚀 Solution

This project leverages [gradle-publisher](https://github.com/zucca-devops-tooling/gradle-publisher) to:

- Dynamically calculate unique artifact versions based on environment (e.g., branch name, PR number).
- Route publishing automatically to different repositories depending on the environment (snapshots vs. releases).
- Simplify Gradle configurations so dynamic versioning and repository selection are automatic and reliable.
- Support realistic build and testing flows by consuming already-published artifacts instead of local source builds.
- Ensure idempotent publishing by verifying whether an artifact already exists before attempting to publish (in production environments only).

## 📈 Impact

- **Reliable** snapshot publishing with no version collisions between engineers.
- **Realistic** simulation of real-world API consumption during CI and functional tests.
- **Safer** artifact management by separating development and production artifacts cleanly.
- **Simplified** developer experience without manual CI conditionals for versioning or repository routing.
- **Rebuild stability**: replaying or rerunning a build does not cause failures with idempotent builds.
---