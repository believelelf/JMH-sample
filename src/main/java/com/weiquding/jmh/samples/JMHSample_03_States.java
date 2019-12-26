/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.weiquding.jmh.samples;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JMHSample_03_States {

    /*
     *  大多数情况下，您需要在基准测试运行时维护某些状态。
     *  由于JMH被大量用于构建并发基准，我们选择了一个明确的状态对象概念。
     *
     * 下面是两个状态对象。它们的类名不是必需的，重要的是用@State标记它们。
     * 这些对象将按需实例化，并在整个基准测试期间重用。
     *
     * 一个重要的概念是，状态总是由一个基准线程实例化，然后该线程将具有对该状态的访问权。
     * 这意味着您可以像在工作线程中那样初始化字段(就像你使用ThreadLocal一样，等等)。
     */

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        volatile double x = Math.PI;
    }

    @State(Scope.Thread)
    public static class ThreadState {
        volatile double x = Math.PI;
    }

    /*
     * 基准测试方法可以引用状态，而JMH将在调用这些方法时注入适当的状态。
     * 您可以完全不引用没有状态，或者引用只有一个状态，或者引用多个状态。
     * 这使得构建多线程基准测试易如反掌。
     *
     * 对于这个练习，我们有下面两种方法。
     */

    @Benchmark
    public void measureUnshared(ThreadState state) {
        // 所有基准测试线程都将调用此方法。
        //
        // 但是因为ThreadState是线程范围的状态，
        // 每个线程都有自己的状态副本，
        // 所以这个基准测试将度量未共享的情况。
        state.x++;
    }

    @Benchmark
    public void measureShared(BenchmarkState state) {
        // 所有基准测试线程都将调用此方法。
        //
        // 因为BenchmarkState是整个基准测试范围的，
        // 基准测试时，所有线程将共享状态实例，
        // 我们最终将度量共享情况。
        state.x++;
    }

    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * 你将看到共享和非共享情况下的巨大差异，
     * 因为状态对象要么争用单个内存位置，要么不争用。
     * 这种效应在大型机器上表现得更为明显。
     *
     * 你能这样运行这个测试:
     *
     * a) 通过命令行:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_03 -t 4 -f 1
     *    (我们以4个线程，单个fork执行，其它命令行可以通过-h显示)
     *
     * b) 通过 Java API:
     *    (从IDE运行时，请参阅JMH主页，了解可能的注意事项:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_03_States.class.getSimpleName())
                .threads(4)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
