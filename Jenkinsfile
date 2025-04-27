pipeline {
    agent any

    environment {
        GRADLE_OPTS = '-Dorg.gradle.jvmargs="-Xmx2g -XX:+HeapDumpOnOutOfMemoryError"'

        JFROG_CREDENTIALS  = credentials('JFROG_CREDENTIALS')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Generate Api') {
            steps {
                sh './gradlew clean --no-daemon :gallery-api:openApiGenerate'
            }
        }
        stage('Publish Api') {
            steps {
                sh './gradlew --info --no-daemon :gallery-api:publish'
            }
        }
        stage('Build App') {
            steps {
                sh './gradlew clean --info --no-daemon :gallery-app:build'
            }
        }
        stage('Publish App') {
            steps {
                sh './gradlew clean --no-daemon :gallery-app:publish'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew clean --no-daemon :functional-test:test'
            }
        }
    }
}