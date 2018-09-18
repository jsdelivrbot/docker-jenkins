import hudson.model.*

def themeColor = System.getenv('JENKINS_THEME_COLOR') ?: 'grey'

for (pd in PageDecorator.all()) {

  if (pd instanceof org.codefirst.SimpleThemeDecorator) {
    def found = false

    for(x in pd.getElements()) {
      if(x.url.startsWith("https://cdn.rawgit.com/afonsof/jenkins-material-theme/gh-pages/dist/material")) {
        x.url = "https://cdn.rawgit.com/afonsof/jenkins-material-theme/gh-pages/dist/material-${themeColor}.css"
        found = true
      }
    }

    if (!found) {
      def te =  new org.jenkinsci.plugins.simpletheme.CssUrlThemeElement("https://cdn.rawgit.com/afonsof/jenkins-material-theme/gh-pages/dist/material-${themeColor}.css")

      pd.setElements([te])
    }
  }
}

