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
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class JMHSample_02_BenchmarkModes {

    /*
     * JMH在基准编译期间产生大量合成代码. JMH可以通过很多模式对基准测试代码进行度量。
     * 用户选择运行通过指定注解标记的默认基准测试模式，或者通过运行时选项选择或指定模式。
     *
     * 在这个场景中，我们将开始度量一些有用的东西。请注意我们的负载代码可能会抛出异常，
     * 而且我们只是声明它们被抛出。如果测试代码真正地抛出一个异常，基准测试将因为错误而停止。
     *
     * 当您对某些特定的行为感到困惑时，查看生成的代码通常是有帮助的。
     * 你可能会看到代码并没有像你假定它执行的那样地执行。好的实验总是对实验设计进行跟踪，
     * 交叉检查生成的代码是跟踪的重要部分。
     *
     * 为这个特定示例生成的代码位于
     * target/generated-sources/annotations/.../JMHSample_02_BenchmarkModes.java
     */

    /*
     * 吞吐量模式，如其Javadoc中所述，通过在一个有时间限制的迭代中不断地
     * 调用benchmark方法并计算我们执行该方法的次数来度量原始吞吐量。
     *
     * 我们使用特定的注解来选择度量单位，虽然你们选择默认的设置。
     */

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureThroughput() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /*
     * 平均时间模式，用于度量方法的平均执行时间，它的工作方式和吞吐量模式是相似的。
     *
     * 有些人可能会说平均时间是吞吐量的倒数，事实的确如此。不过，有些工作负载更方便地测量时间。
     */

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureAvgTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /**
     * SampleTime模式基于对执行时间进行采样。使用这种模式，我们仍然在一个有时间限制的迭代中运行方法，
     * 但是我们测量的不是总时间，而是在一些基准方法调用中花费的时间。
     *
     * 这使我们能够推断分布、百分位数等。
     *
     * JMH还试图自动调整采样频率:如果方法足够长，你最终将捕获所有的样本。
     */
    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureSamples() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /**
     * SingleShotTime模式度量单个方法调用时间。正如Javadoc所说明的，
     * 我们只执行单个基准方法调用。在这种模式下，迭代时间是没有意义的:
     * 一旦基准方法结束，迭代就结束了。
     *
     * 这种模式对于执行冷启动测试非常有用，特别是当您不想连续调用基准测试方法时。
     */
    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureSingleShot() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /**
     * 我们还可以同时要求多个基准测试模式。上面的所有测试都可以替换为这样的单个测试：
     */
    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureMultiple() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /*
     * 甚至...
     */

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureAll() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * 您将看到相同基准测试的不同运行模式。注意单位不同，分数是一致的。
     *
     * 你能这样运行这个测试:
     *
     * a) 通过命令行:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_02 -f 1
     *    (我们只要求了单个fork;其他命令行选项，可以通过-h显示)
     *
     * b) 通过Java API:
     *    (从IDE运行时，请参阅JMH主页，了解可能的注意事项:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_02_BenchmarkModes.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
