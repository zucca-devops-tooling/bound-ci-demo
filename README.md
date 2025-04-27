# Bound CI Demo

A multi-module Gradle project demonstrating dynamic versioning, API contract publishing, and real artifact consumption during build pipelines.  
Built to simulate real-world CI environments handling multiple PRs and snapshot collisions.

## 🎯 Goal

- Generate an API using OpenAPI Generator in a separate module.
- Publish the API as a standalone artifact with its own versioning.
- Consume the published API as a dependency instead of compiling it locally.
- Simulate functional testing scenarios by downloading the published API.

## 🐞 Problem

- **Versioning Collisions**:  
  When multiple engineers work on the same base version (e.g., `2.5.0`), publishing `-SNAPSHOT` artifacts can lead to overwrites and collisions.
- **Producer-Consumer Separation**:  
  Without publishing, consumers are forced to compile sources locally, breaking real-world usage patterns.
- **Repository Pollution**:  
  Mixing snapshots and releases into the same repository increases risks of accidental release pollution.
- **Static Versioning in CI**:  
  Without dynamic versioning based on the branch or PR, snapshot uploads are unreliable in parallel CI environments.

## 🚀 Solution

This project leverages [gradle-publisher](https://github.com/zucca-devops-tooling/gradle-publisher) to:

- Dynamically calculate unique artifact versions based on environment (e.g., branch name, PR number).
- Route publishing automatically to different repositories depending on the environment (snapshots vs. releases).
- Simplify Gradle configurations so dynamic versioning and repository selection are automatic and reliable.
- Support realistic build and testing flows by consuming already-published artifacts instead of local source builds.

## 📈 Impact

- **Reliable** snapshot publishing with no version collisions between engineers.
- **Realistic** simulation of real-world API consumption during CI and functional tests.
- **Safer** artifact management by separating development and production artifacts cleanly.
- **Simplified** developer experience without manual CI conditionals for versioning or repository routing.

---