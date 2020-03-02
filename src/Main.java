import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Main {

    private static final String readdir = "./data/input/";
    private static final String writedir = "./data/output/";
    private String file;

    public Main(String file) {
        this.file = file;
    }

    // 读取文件全部数据(4, ?)
    public String[][] read() throws BiffException, IOException {

        File xlsFile = new File(readdir + this.file);
        // 获得工作簿对象
        Workbook workbook = Workbook.getWorkbook(xlsFile);
        // 获得所有工作表
        Sheet[] sheets = workbook.getSheets();
        // 遍历工作表
        if (sheets != null)
        {
            Sheet sheet = sheets[0];
            // 获得行数
            int rows = sheet.getRows();
            // 获得列数
            int cols = sheet.getColumns();
            //数据存储项
            String[][] data = new String[cols][rows];

            // 读取数据
            for (int col = 0; col < cols; col++)
            {
                for (int row = 0; row < rows; row++)
                {
                    data[col][row] = sheet.getCell(col, row).getContents();//todo
                }
            }
            workbook.close();
            return data;
        }

        workbook.close();
        return null;
    }

    // 将分好词的数据全部写入excel中(4, ?)
    public void write(String[][] data) throws IOException, WriteException {
        File xlsFile = new File(writedir + file);
        // 创建一个工作簿
        WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
        // 创建一个工作表
        WritableSheet sheet = workbook.createSheet("sheet1", 0);
        for (int col = 0; col < data.length; col++)
        {
            for (int row = 0; row < data[0].length; row++)
            {
                // 向工作表中添加数据
                sheet.addCell(new Label(col, row, data[col][row]));
            }
        }
        workbook.write();
        workbook.close();
    }

    private String[][]  segment(String[][] data){
        String[][] out =new String[data.length][data[0].length];
        for(int i = 0; i<data.length; i++){
            for(int j=0; j<data[0].length; j++){
                if(i == 3){
                    List<Word> words = WordSegmenter.segWithStopWords(data[i][j]);//todo 保留了停止词
                    StringBuffer string = new StringBuffer();
                    for(int k = 0; k<words.size();k++){
                        if(k == 0){
                            string.append(words.get(k).getText());
                        }else {
                            string.append(" ");
                            string.append(words.get(k).getText());
                        }
                    }
                    out[i][j] = string.toString();
                    System.out.println(out[i][j]);
                } else {
                    out[i][j] = data[i][j];
                }
            }
        }
        return out;
    }

    public static void main(String[] args) {
        try{
            Main train_segmenter = new Main("train.xls");
            train_segmenter.write(train_segmenter.segment(train_segmenter.read()));

            Main test_segmenter = new Main("test.xls");
            test_segmenter.write(test_segmenter.segment(test_segmenter.read()));

            Main val_segmenter = new Main("val.xls");
            val_segmenter.write(val_segmenter.segment(val_segmenter.read()));

        }catch (BiffException biffex){
            biffex.printStackTrace();
        }catch (IOException ioex){
            ioex.printStackTrace();
        }catch (WriteException wrex){
            wrex.printStackTrace();
        }
    }
}
