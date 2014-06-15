# 20140614-JavaVM-GCLog #

- [2014-06-14 土 16:00] begin
- [2014-06-14 土 17:53] end

## github repository ##

- F:\goat-pc-data\ecworkspace\20140614-GCLog\wiki\20140614-JavaVM-GCLog.md
- git clone https://github.com/tempest200903/20140614-GCLog

## 実験 ##

- BodEngineContinuousIntegration-d20140325.iit from iThoughts
    - 工程毎にヒープメモリ使用量を見える化したい。
    - GCViewer では全体を見える化できるが、工程別はできない。
- F:\goat-pc-data\ecworkspace\20140614-GCLog\src\main\java\com\example\GC.java

    ```
    > public static void main(String[] args) throws InterruptedException {
    > 		List<String> list = new ArrayList<String>();
    > 		for (int i = 0;; i++) {
    > 			list.add("a");
    > 			if (i % Integer.MAX_VALUE / 10 == 0) {
    > 				printHeap();
    > 				Thread.sleep(1);
    > 				System.gc();
    > 				System.out.println(new Date());				
    > 			}
    > 		}
    > 	}
    ```

- java -Xms10M -Xmx20M -Xloggc:logs/gc.log -XX:+PrintGCDateStamps com.example.GC
- System.out

    ```
    > (中略)
    > init =: 10485760, used =: 614368, committed =: 10682368, max =: 18677760
    > 2014-06-14T17:26:12.467
    > init =: 10485760, used =: 614368, committed =: 10682368, max =: 18677760
    > 2014-06-14T17:26:12.475
    > Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    ```

- 【System.out 1】 17:26:12.475 で分割したい

- F:\goat-pc-data\ecworkspace\20140614-GCLog\logs\gc.log

    ```
    > (中略)
    > 2014-06-14T17:26:12.461+0900: 0.245: [GC 599K->538K(10432K), 0.0001945 secs]
    > 2014-06-14T17:26:12.461+0900: 0.246: [Full GC 538K->538K(10432K), 0.0063353 secs]
    > 2014-06-14T17:26:12.469+0900: 0.253: [GC 599K->538K(10432K), 0.0001857 secs]
    > 2014-06-14T17:26:12.469+0900: 0.254: [Full GC 538K->538K(10432K), 0.0059858 secs]
    > 【System.out 1】 17:26:12.475 ここで分割する
    > 2014-06-14T17:26:12.484+0900: 0.269: [GC 3365K->2478K(10432K), 0.0025134 secs]
    > 2014-06-14T17:26:12.500+0900: 0.285: [GC 13908K->12469K(19968K), 0.0030574 secs]
    > 2014-06-14T17:26:12.503+0900: 0.288: [GC 12469K->12469K(19968K), 0.0030202 secs]
    > 2014-06-14T17:26:12.507+0900: 0.291: [Full GC 12469K->5286K(17024K), 0.0186489 secs]
    > 2014-06-14T17:26:12.533+0900: 0.317: [GC 12408K->12408K(19968K), 0.0075176 secs]
    > 2014-06-14T17:26:12.540+0900: 0.325: [GC 12408K->12408K(19520K), 0.0043946 secs]
    > 2014-06-14T17:26:12.545+0900: 0.330: [Full GC 12408K->7660K(19520K), 0.0246368 secs]
    > 2014-06-14T17:26:12.570+0900: 0.354: [GC 7660K->7660K(19776K), 0.0002236 secs]
    > 2014-06-14T17:26:12.570+0900: 0.355: [Full GC 7660K->7644K(19776K), 2.2203403 secs]
    ```

- GCViewer
    - http://sourceforge.net/projects/gcviewer/
    - F:\goat-pc-data\download\irvine\y2014\programming\java\gcviewer\gcviewer-1.34-SNAPSHOT.jar

- file:F:\goat-pc-data\ecworkspace\20140614-GCLog\screenshot\screenshot-g-000101.jpg
    - https://github.com/tempest200903/20140614-GCLog/blob/master/screenshot/screenshot-g-000101.jpg
- 分割した GC ログをそれぞれ GCViewer で開いた。
    - gc-split1.log Total heap max 10.2 M
    - gc-split2.log Total heap max 19.5 M
