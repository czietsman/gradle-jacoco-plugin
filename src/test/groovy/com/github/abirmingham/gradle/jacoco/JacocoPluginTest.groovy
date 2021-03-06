package com.github.abirmingham.gradle.jacoco

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertTrue

class JacocoPluginTest {

    Project project

    @Before
    public void setup() {
        project = ProjectBuilder.builder().build()
        project.apply plugin: 'jacoco'
        project.repositories { mavenCentral() }
    }

    @Test
    public void shouldAddExtension() {
        assertNotNull(project.extensions.getByName('jacoco'))
    }

    @Test
    public void shouldSetJvmArgs() {
        assertNotNull(project.test)
        project.test.execute()

        assertTrue(project.getTasks().findByName('test').jvmArgs.any { it.matches('.*jacoco\\.exec[,$].*') })
        // e.g. -javaagent:/var/folders/vv/9vg9d_f975q9s_rl8gbdzbk00000gn/T/jacocoagent8666481667761165084.jar=destfile=/private/var/folders/vv/9vg9d_f975q9s_rl8gbdzbk00000gn/T/gradle40938656179684856projectDir/build/tmp/jacoco/jacoco.exec,append=false,dumponexit=true,output=file
    }

    // TBD test that configurations are added before their values are captured during plugin execution
}
