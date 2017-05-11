/*
 * Copyright 2015 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.benoitquenaudon.rxdatabinding.project

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class RxDataBindingPlugin : Plugin<Project> {

  companion object {
    private val INCLUDE_PATTERN = "**/Rx*.java"
    private val EXCLUDE_PATTERN = "**/internal/*"
  }

  override fun apply(project: Project) {
    project.afterEvaluate {

      // Grab the release variant
      // Convenience approach so that we can grab the source sets off of it

      val variants = project.extensions.getByType(LibraryExtension::class.java).libraryVariants
      val variant = variants.first { it.name == "release" }
      val variantJavaSources = variant.sourceSets.map { it.javaDirectories }

      // Task for validating Rx*.java factory method structures
      val validateBindingsTask
          = project.tasks.create("validateBindings", ValidateBindingsTask::class.java).apply {
        source(variantJavaSources)
        include(INCLUDE_PATTERN)
        exclude(EXCLUDE_PATTERN)
      }

      project.tasks.findByName("check").dependsOn(validateBindingsTask)
    }
  }
}
