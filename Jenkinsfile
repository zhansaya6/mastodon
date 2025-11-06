pipeline {
  agent any

  tools {
    maven 'M3'
  }

  options {
    ansiColor('xterm')
    timeout(time: 30, unit: 'MINUTES')
  }

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Env check') {
      steps {
        sh '''
          whoami
          echo "JAVA_HOME=$JAVA_HOME"
          which mvn || true
          mvn -v
        '''
      }
    }

    stage('Build & Test') {
      steps {
        withCredentials([usernamePassword(
          credentialsId: 'bstack-creds',
          usernameVariable: 'BROWSERSTACK_USERNAME',
          passwordVariable: 'BROWSERSTACK_ACCESS_KEY'
        )]) {
          sh '''
            mvn -B clean test \
              -DBROWSERSTACK_USERNAME="$BROWSERSTACK_USERNAME" \
              -DBROWSERSTACK_ACCESS_KEY="$BROWSERSTACK_ACCESS_KEY"
          '''
        }
      }
    }

    stage('Allure Report') {
      steps {
        allure(
          includeProperties: false,
          jdk: '',
          results: [[path: 'target/allure-results']],
          reportBuildPolicy: 'ALWAYS'
        )
      }
    }
  }

  post {
    always { echo "Build finished with: ${currentBuild.currentResult}" }
  }
}
