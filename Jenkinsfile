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
                sh './gradlew clean --no-daemon :gallery-api:openApiGenerate -PJFROG_CREDENTIALS_USR=$JFROG_CREDENTIALS_USR -PJFROG_CREDENTIALS_PSW=$JFROG_CREDENTIALS_PSW'
            }
        }
        stage('Publish Api') {
            steps {
                sh './gradlew clean --info --no-daemon :gallery-api:publish -PJFROG_CREDENTIALS_USR=$JFROG_CREDENTIALS_USR -PJFROG_CREDENTIALS_PSW=$JFROG_CREDENTIALS_PSW'
            }
        }
        stage('Build App') {
            steps {
                sh './gradlew clean --resolve-dependencies --no-daemon :gallery-app:build -PJFROG_CREDENTIALS_USR=$JFROG_CREDENTIALS_USR -PJFROG_CREDENTIALS_PSW=$JFROG_CREDENTIALS_PSW'
            }
        }
        stage('Publish App') {
            steps {
                sh './gradlew clean --no-daemon :gallery-app:publish -PJFROG_CREDENTIALS_USR=$JFROG_CREDENTIALS_USR -PJFROG_CREDENTIALS_PSW=$JFROG_CREDENTIALS_PSW'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew clean --no-daemon :functional-test:test -PJFROG_CREDENTIALS_USR=$JFROG_CREDENTIALS_USR -PJFROG_CREDENTIALS_PSW=$JFROG_CREDENTIALS_PSW'
            }
        }
    }
}