import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Word> words = WordSegmenter.seg("郭晓珂在四川大学");
        System.out.println(words);
    }
}
