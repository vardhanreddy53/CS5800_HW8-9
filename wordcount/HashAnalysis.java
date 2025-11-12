import java.util.Arrays;

import javax.swing.*;
import java.awt.*;

public class HashAnalysis {
    public static double variance(int[] lengths){
        int n=lengths.length;
        double sum=0;
        for(int i:lengths)
        sum+=i;

        double mean=sum/n;
        double var=0;

        for(int i:lengths)
        var+=(i-mean)*(i-mean);

        return var/(n-1);
    }
    public static void printhist(int[] lengths){
        System.out.println("\n---------Histogram------------");
        for(int i=0;i<lengths.length;i++){
            System.out.println("Bucket"+i+":"+lengths[i]);
        }
    }
    public static void printTopList(int[] lengths){
        int[] temp=Arrays.copyOf(lengths,lengths.length);
        Arrays.sort(temp);
        int n=(int)Math.ceil(temp.length*0.1);
        System.out.println("lengths of top 10% of lists");
        for(int i=temp.length-n;i<temp.length;i++){
            System.out.print(temp[i]+" ");
        }
        System.out.println();
    }
    public static void showHistogram(int[] lengths) {
    int barWidth = 20; // fixed width for each bar
    int n = lengths.length;
    int panelWidth = barWidth*n+100; // add some padding
    int panelHeight = 500;

    JFrame frame = new JFrame("Histogram");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(panelWidth, panelHeight);

    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int max = Arrays.stream(lengths).max().orElse(1);
            for (int i = 0; i < n; i++) {
                int barHeight = (int) (((double) lengths[i] / max) * (panelHeight - 50));
                int x = i * barWidth + 25; // add left padding
                int y = panelHeight - barHeight - 30; // add bottom padding

                g.setColor(Color.red);
                g.fillRect(x, y, barWidth - 2, barHeight);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, barWidth - 2, barHeight);

                g.setColor(Color.BLACK);
                if (barWidth >= 10) { // only if there's space
                    g.drawString(String.valueOf(i), x, panelHeight - 10);//writting bucket number under the bar
                }
            }
        }
    };

    panel.setPreferredSize(new Dimension(panelWidth, panelHeight));
    JScrollPane scrollPane = new JScrollPane(panel); // allows horizontal scrolling if too many bars
    frame.add(scrollPane);
    frame.pack();
    frame.setVisible(true);
    }
    
}
