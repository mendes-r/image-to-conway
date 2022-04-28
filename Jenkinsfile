pipeline {

    agent any

    stages {
        stage('Build'){
            steps {
                echo '||||||||||| Building ...'
                sh './gradlew clean build -x test'
            }
        }

        stage('Test'){
            steps {
                echo '||||||||||| Testing ...'
                sh './gradlew test'
            }
        }

        stage('Sonarqube'){
            environment {
                SONAR_CREDENTIALS = credentials('sonar-credentials')
                SONAR_URL='http://sonar:9000'
            }
            steps {
                echo '||||||||||| Sonar analysis ...'
                sh './gradlew sonar -Dsonar.host.url=$SONAR_URL -Dsonar.login=$SONAR_CREDENTIALS_USR -Dsonar.password=$SONAR_CREDENTIALS_PSW'
            }
        }

        stage('Build Image'){
            when {
                branch 'main'
            }
            steps {
                echo '||||||||||| Building Docker image...'
                sh './gradlew docker --stacktrace'
            }
        }

        stage('Push Docker Image'){
            when {
                branch 'main'
            }
            steps {
                echo '||||||||||| Pushing Docker image ...'
                sh './gradlew dockerTagDockerHub'
                sh './gradlew dockerPushDockerHub'
            }
        }
    }

    post {
        always {
            junit 'build/test-results/**/*.xml'
            cleanWs()
        }
    }
}