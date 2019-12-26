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
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JMHSample_01_HelloWorld {

    /*
     *
     * 这是我们第一个基准测试方法。
     * JMH是这样工作的：用户在方法上标注@Benchmark注解，然后JMH通过生成合成代码来尽可能地可靠运行基准测试。
     * 通过我们可以把@Benchmark标记的方法看作基准测试的有效负载，即我们要度量的东西。在方法周围的基础设施
     * 由JMH工具自身提供。
     *
     * 请阅读@Benchmark注释的Javadoc以获得完整的语义和限制。
     * 此时我们注意到基准测试方法的方法名不是重要的，只要标记了@Benchmark注解即可。
     * 在一个类中你能写多个基准测试方法。
     *
     * 注意: 如基准测试方法永不结束，基准测试也永不会结束。如果基准测试方法中抛出一个
     * 异常，那JMH对这个方法的基准测试将突然中止，并将开始运行列表中下一个方法的基准测试。
     *
     * 虽然这个基准测试没有任何东西进行度量，但它是一个很好的示例，
     * 它展示了度量这个方法上基础设施所花费的开销。没有不产生开销的基础设施，但重要的是
     * 你要知道你正在执行基准测试中基础设施的开销是多少。
     * 你可以在未来的示例中，发现这种通过基准线比较度量的思想。
     */

    @Benchmark
    public void wellHelloThere() {
        // this method was intentionally left blank.
    }

    /*
     * ============================== 怎么运行这个示例: ====================================
     *
     * 您将看到大量的迭代和非常大的吞吐量。您将看到执行每个方法调用的工具的开销估计值。
     * 在我们的大多数测量中，每次调用可以缩短到几个时钟周期。
     *
     * a) 通过命令行:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_01
     *
     * JMH生成自包含的JAR包，通过将JMH与测试方法绑定在一起。
     * JMH运行时可以附加命令行选项-h
     *    $ java -jar target/benchmarks.jar -h
     *
     * b) 通过Java API:
     *    (从IDE运行时，请参阅JMH主页，了解可能的注意事项:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_01_HelloWorld.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}

/**
 WARNING: An illegal reflective access operation has occurred
 WARNING: Illegal reflective access by org.openjdk.jmh.util.Utils (file:/E:/Java/idea_workspaces/jmh-samples/target/benchmarks.jar) to field java.io.Console.cs
 WARNING: Please consider reporting this to the maintainers of org.openjdk.jmh.util.Utils
 WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
 WARNING: All illegal access operations will be denied in a future release
 # JMH version: 1.21
 # VM version: JDK 10.0.2, Java HotSpot(TM) 64-Bit Server VM, 10.0.2+13
 # VM invoker: E:\Program Files\Java\jre-10.0.2\bin\java.exe
 # VM options: <none>
 # Warmup: 5 iterations, 10 s each
 # Measurement: 5 iterations, 10 s each
 # Timeout: 10 min per iteration
 # Threads: 1 thread, will synchronize iterations
 # Benchmark mode: Throughput, ops/time
 # Benchmark: com.weiquding.jmh.samples.JMHSample_01_HelloWorld.wellHelloThere

 # Run progress: 0.00% complete, ETA 00:08:20
 # Fork: 1 of 5
 # Warmup Iteration   1: 2394463497.502 ops/s
 # Warmup Iteration   2: 2407848615.120 ops/s
 # Warmup Iteration   3: 1777463606.748 ops/s
 # Warmup Iteration   4: 1839136181.059 ops/s
 # Warmup Iteration   5: 1818779403.697 ops/s
 Iteration   1: 1833436790.558 ops/s
 Iteration   2: 1801974842.499 ops/s
 Iteration   3: 1800063603.705 ops/s
 Iteration   4: 1813189914.574 ops/s
 Iteration   5: 1847162636.126 ops/s

 # Run progress: 20.00% complete, ETA 00:06:41
 # Fork: 2 of 5
 # Warmup Iteration   1: 2455065245.987 ops/s
 # Warmup Iteration   2: 2530350692.262 ops/s
 # Warmup Iteration   3: 1876962498.772 ops/s
 # Warmup Iteration   4: 1865093075.634 ops/s
 # Warmup Iteration   5: 1874304991.637 ops/s
 Iteration   1: 1881071769.385 ops/s
 Iteration   2: 1886869892.115 ops/s
 Iteration   3: 1886429661.531 ops/s
 Iteration   4: 1861268818.782 ops/s
 Iteration   5: 1831072598.875 ops/s

 # Run progress: 40.00% complete, ETA 00:05:01
 # Fork: 3 of 5
 # Warmup Iteration   1: 2396166298.343 ops/s
 # Warmup Iteration   2: 2480185906.657 ops/s
 # Warmup Iteration   3: 1859640248.770 ops/s
 # Warmup Iteration   4: 1851923012.759 ops/s
 # Warmup Iteration   5: 1846951759.686 ops/s
 Iteration   1: 1860724054.156 ops/s
 Iteration   2: 1774831919.289 ops/s
 Iteration   3: 1859930846.957 ops/s
 Iteration   4: 1858756396.749 ops/s
 Iteration   5: 1885794851.159 ops/s

 # Run progress: 60.00% complete, ETA 00:03:20
 # Fork: 4 of 5
 # Warmup Iteration   1: 2474432979.739 ops/s
 # Warmup Iteration   2: 2521374362.830 ops/s
 # Warmup Iteration   3: 1892309627.168 ops/s
 # Warmup Iteration   4: 1862082781.660 ops/s
 # Warmup Iteration   5: 1912625547.571 ops/s
 Iteration   1: 1888856415.630 ops/s
 Iteration   2: 1888738336.578 ops/s
 Iteration   3: 1885823154.037 ops/s
 Iteration   4: 1903341299.657 ops/s
 Iteration   5: 1705261670.956 ops/s

 # Run progress: 80.00% complete, ETA 00:01:40
 # Fork: 5 of 5
 # Warmup Iteration   1: 2138831005.697 ops/s
 # Warmup Iteration   2: 2268456478.217 ops/s
 # Warmup Iteration   3: 1738458497.310 ops/s
 # Warmup Iteration   4: 1888903901.217 ops/s
 # Warmup Iteration   5: 1800423360.215 ops/s
 Iteration   1: 1763223947.320 ops/s
 Iteration   2: 1745356265.459 ops/s
 Iteration   3: 1794538826.395 ops/s
 Iteration   4: 1883548817.929 ops/s
 Iteration   5: 1885827096.757 ops/s


 Result "com.weiquding.jmh.samples.JMHSample_01_HelloWorld.wellHelloThere":
 1841083777.087 ±(99.9%) 39832528.047 ops/s [Average]
 (min, avg, max) = (1705261670.956, 1841083777.087, 1903341299.657), stdev = 53175285.325
 CI (99.9%): [1801251249.040, 1880916305.134] (assumes normal distribution)


 # Run complete. Total time: 00:08:21

 REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
 why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
 experiments, perform baseline and negative tests that provide experimental control, make sure
 the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
 Do not assume the numbers tell you what you want them to tell.

 Benchmark                                Mode  Cnt           Score          Error  Units
 JMHSample_01_HelloWorld.wellHelloThere  thrpt   25  1841083777.087 ± 39832528.047  ops/s
*/