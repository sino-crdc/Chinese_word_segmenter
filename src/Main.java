import org.apdplat.word.WordFrequencyStatistics;
import org.apdplat.word.segmentation.SegmentationAlgorithm;

import java.io.File;

public class Main {

    private Main() {
    }

    public static void main(String[] args) {
        //遍历文件夹中所有txt
        //对每个txt分析词频
        //将词频统计结果存放在平行文件夹中
        //词频统计设置
        WordFrequencyStatistics wordFrequencyStatistics = new WordFrequencyStatistics();
        wordFrequencyStatistics.setRemoveStopWord(false);
//        wordFrequencyStatistics.setResultPath("word-frequency-statistics.txt");
        wordFrequencyStatistics.setSegmentationAlgorithm(SegmentationAlgorithm.MaxNgramScore);

        String path = args[0];//data\input
        String segoutpath = args[1];//data\segout
        String statoutpath = args[2];//data\statout

        File[] files = new File(path).listFiles();
        if (new File(statoutpath).mkdir()) {
            for (File f : files) {
                try {
                    wordFrequencyStatistics.seg(f, new File(segoutpath + File.separator + "segresults_" + f.getName()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                wordFrequencyStatistics.dump(statoutpath + File.separator + "statresults_" + f.getName());
            }
        } else {
            System.out.println("没有相应的词频输出文件夹或者该文件夹已存在，需要删除后重新运行这个程序。");
        }
    }
}
