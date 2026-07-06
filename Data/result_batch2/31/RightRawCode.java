// https://github.com/Exrick/xboot/tree/5277af0ea7db3cf085f8ef3be21329df5bb12cd9/xboot-fast/src/main/java/cn/exrick/xboot/common/utils/CreateVerifyCode.java#L152-L221
public class TempClass {
        int codeY = height - 8;

        // 图像buffer
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = buffImg.getGraphics();
        //Graphics2D g = buffImg.createGraphics();
        // 设置背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);

        // 设置字体
        //Font font1 = getFont(fontHeight);
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
        g.setFont(font);

        // 设置干扰线
        for (int i = 0; i < lineCount; i++) {
            int xs = random.nextInt(width);
            int ys = random.nextInt(height);
            int xe = xs + random.nextInt(width);
            int ye = ys + random.nextInt(height);
            g.setColor(getRandColor(1, 255));
            g.drawLine(xs, ys, xe, ye);
        }

        // 添加噪点 噪声率
        float yawpRate = 0.01f;
        int area = (int) (yawpRate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);

            buffImg.setRGB(x, y, random.nextInt(255));
        }

        this.code = code;
        for (int i = 0; i < code.length(); i++) {
            String strRand = code.substring(i, i + 1);
            g.setColor(getRandColor(1, 255));
            // g.drawString(a,x,y);
            // a为要画出来的东西，x和y表示要画的东西最左侧字符的基线位于此图形上下文坐标系的 (x, y) 位置处
            g.drawString(strRand, i * fontWidth + 3, codeY);
        }

    }

    /**
     * 得到随机字符
     * @param n
     * @return
     */
    public String randomStr(int n) {

        String str = "";
        int len = STRING.length() - 1;
        double r;
        for (int i = 0; i < n; i++) {
            r = random.nextDouble() * len;
            str = str + STRING.charAt((int) r);
        }
        return str;
    }

    /**
     * 得到随机颜色
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {

}