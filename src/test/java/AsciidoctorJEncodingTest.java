/*
 * Copyright 2021 - 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;
import org.asciidoctor.SafeMode;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

/**
 * @author Fabian Krüger
 */
public class AsciidoctorJEncodingTest {
    @Test
    void asciidoctorJdefault() {
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();
        String backend = "html5";
        generateHtml(asciidoctor, backend);
    }

    @Test
    void springHtml() {
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();
        String backend = "spring-html";
        generateHtml(asciidoctor, backend);
    }

    private void generateHtml(Asciidoctor asciidoctor, String backend) {
        String html = asciidoctor.convert("""
                                                [[index]]
                                                = Spring Boot 3 Upgrade Report
                                                Fabian Krüger
                                                :source-highlighter: highlight.js
                                                :highlightjs-languages: java
                                                :linkcss:
                                                :doctype: book
                                                :idprefix:
                                                :idseparator: -
                                                :toc: left
                                                :sectnumlevels: 2
                                                :toclevels: 2
                                                :tabsize: 4
                                                :numbered:
                                                :sectanchors:
                                                :sectnums:
                                                :hide-uri-scheme:
                                                :docinfo: shared,private
                                                :attribute-missing: warn
                                                :chomp: default headers packages
                                                :spring-boot-artifactory-repo: snapshot
                                                :github-tag: main
                                                :spring-boot-version: current

                                                == Introduction
                                                [cols="1h,3"]
                                                |===
                                                | Scanned dir | `/Users/fkrueger/projects/spring-boot-migrator/components/sbm-recipes-boot-upgrade/target/dummy-test-path`
                                                | Revision | Scanned project not under Git
                                                | Coordinate | `com.example:dummy-root:0.1.0-SNAPSHOT`
                                                | Boot version | `2.7.3`
                                                | Changes | 1
                                                |===

                                                The application was scanned and matched against the changes listed in the
                                                https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.5-Release-Notes[Spring Boot 2.5 Release Notes]
                                                as well as from https://github.com/spring-projects/spring-framework/wiki/Upgrading-to-Spring-Framework-5.x[Spring Framework 5.x Release Notes].

                                                The Relevant Changes section lists all potentially required changes to upgrade the scanned application to Spring Boot 2.5.6.

                                                NOTE: JDK 17 is required for Spring Boot 3

                                                == Relevant Changes

                                                This section lists the changes SBM found to be applicable to upgrade the scanned application to Spring Boot 3.0.0.s

                                                === Changes to Data Properties
                                                Issue: https://github.com/spring-projects-experimental/spring-boot-migrator/issues/123[#123], Contributors: https://github.com/fabapp2[@fabapp2^, role="ext-link"]

                                                ==== What Changed
                                                The data prefix has been reserved for Spring Data and any properties under the `data` prefix imply that Spring
                                                Data is required on the classpath.

                                                ==== Why is the application affected
                                                The scan found properties with `spring.data` prefix but no dependency matching `org.springframework.data:.*`.

                                                  * file:///Users/fkrueger/projects/spring-boot-migrator/components/sbm-recipes-boot-upgrade/target/dummy-test-path/src/main/resources/application.properties[`src/main/resources/application.properties`]
                                                  ** `spring.data.foo`
                                                  * file:///Users/fkrueger/projects/spring-boot-migrator/components/sbm-recipes-boot-upgrade/target/dummy-test-path/src/main/resources/application-another.properties[`src/main/resources/application-another.properties`]
                                                  ** `spring.data.here`

                                                ==== Remediation
                                                Either add `spring-data` dependency, rename the property or remove it in case it's not required anymore.



                                                We want to say thank you to all Contributors:

                                                Generated by Spring Boot Migrator (experimental)
                                                  """,
                                          Options.builder()
                                                  .toFile(true)
                                                  .backend(backend)
                                                  .headerFooter(true)
                                                  .safe(SafeMode.SERVER)
                                                  .build(

                                                  ));

        System.out.println("USING CHARSET: " + Charset.defaultCharset());
//        System.out.println(html);
    }
}
