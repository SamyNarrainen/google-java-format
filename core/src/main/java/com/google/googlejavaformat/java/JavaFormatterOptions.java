/*
 * Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.googlejavaformat.java;

import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.Immutable;

/**
 * Options for a google-java-format invocation.
 *
 * <p>Like gofmt, the google-java-format CLI exposes <em>no</em> configuration options (aside from
 * {@code --aosp}).
 *
 * <p>The goal of google-java-format is to provide consistent formatting, and to free developers
 * from arguments over style choices. It is an explicit non-goal to support developers' individual
 * preferences, and in fact it would work directly against our primary goals.
 */
@Immutable
@AutoValue
public abstract class JavaFormatterOptions {

  public enum Style {
    /** The default Google Java Style configuration. */
    GOOGLE(1),

    /** The AOSP-compliant configuration. */
    AOSP(2),
    
    /** A slightly customised implementation of the Google Java Style configuration */
    CUSTOM_GOOGLE(2),
    ;

    private final int indentationMultiplier;

    Style(int indentationMultiplier) {
      this.indentationMultiplier = indentationMultiplier;
    }

    int indentationMultiplier() {
      return indentationMultiplier;
    }
  }

  /** Returns the multiplier for the unit of indent. */
  public int indentationMultiplier() {
    return style().indentationMultiplier();
  }

  public abstract boolean formatJavadoc();

  public abstract boolean reorderModifiers();

  /** Returns the code style. */
  public abstract Style style();

  /** Returns the default formatting options. */
  public static JavaFormatterOptions defaultOptions() {
    return builder().build();
  }

  /** Returns a builder for {@link JavaFormatterOptions}. */
  public static Builder builder() {
    return new AutoValue_JavaFormatterOptions.Builder()
        .style(Style.CUSTOM_GOOGLE)
        .formatJavadoc(true)
        .reorderModifiers(true);
  }

  /** A builder for {@link JavaFormatterOptions}. */
  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder style(Style style);

    public abstract Builder formatJavadoc(boolean formatJavadoc);

    public abstract Builder reorderModifiers(boolean reorderModifiers);

    public abstract JavaFormatterOptions build();
  }
}
