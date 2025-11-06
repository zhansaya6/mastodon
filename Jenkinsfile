stage('Env check') {
  steps {
    bat '''
      whoami
      echo JAVA_HOME=%JAVA_HOME%
      where mvn
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
      bat '''
        echo BS user: %BROWSERSTACK_USERNAME%
        if "%BROWSERSTACK_ACCESS_KEY%"=="" (echo BS key set? no) else (echo BS key set? yes)

        mvn -B clean test ^
          -DBROWSERSTACK_USERNAME=%BROWSERSTACK_USERNAME% ^
          -DBROWSERSTACK_ACCESS_KEY=%BROWSERSTACK_ACCESS_KEY%
      '''
    }
  }
}

stage('Allure Report') {
  steps {
    allure(
      includeProperties: false,
      jdk: '',
      results: [[path: 'target\\allure-results']],
      reportBuildPolicy: 'ALWAYS'
    )
  }
}
