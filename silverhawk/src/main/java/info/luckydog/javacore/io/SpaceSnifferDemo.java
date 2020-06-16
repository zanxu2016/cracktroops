package info.luckydog.javacore.io;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class SpaceSnifferDemo {

    private static final int DEPTH = 3;

    @Test
    public void test() throws IOException {
//        String dirPath = "E:/";
        String dirPath = "E:\\download\\spacesniffer_1_3_0_2";
        File bootFile = new File(dirPath);
        getSubFileList(bootFile).forEach(file -> {
            // 名称
            String name = file.getName();
            if (StringUtils.isBlank(name)) {
                name = file.toString();
            }
            System.out.println(name);

            // 占用空间
            long totalSpace = file.getTotalSpace();
            long freeSpace = file.getFreeSpace();
            long usedSpace = totalSpace - freeSpace;
            System.out.println(usedSpace);

            System.out.println();


        });
    }

    public List<File> getSubFileList(File bootFile) throws IOException {
        List<File> files = new ArrayList<>();
        files.add(bootFile);
        if (bootFile.isDirectory()) {
            File[] subFiles = bootFile.listFiles();
            if (subFiles == null) return files;
            for (File subFile : subFiles) {
                List<File> subFileList = getSubFileList(subFile);
                files.addAll(subFileList);
            }
        }
        return files;
    }

    @Test
    public void testTree() throws IOException {
        TreeMap<String, List<File>> fileTreeMap = new TreeMap<>();
        String rootPath = "E:\\";
        File bootFile = new File(rootPath);
        fileTreeMap.put(rootPath, getSubFileList(bootFile));
        System.out.println(fileTreeMap);
    }

    @Test
    public void testFile() throws IOException {
        String rootPath = "E:\\download\\spacesniffer_1_3_0_2";
        File bootFile = new File(rootPath);
        System.out.println("bootFile.getName() -> " + bootFile.getName());
        System.out.println("bootFile.toString() -> " + bootFile.toString());
        System.out.println("bootFile.getPath() -> " + bootFile.getPath());
        System.out.println("bootFile.getAbsolutePath() -> " + bootFile.getAbsolutePath());
        System.out.println("bootFile.getAbsoluteFile() -> " + bootFile.getAbsoluteFile());
        System.out.println("bootFile.getCanonicalPath() -> " + bootFile.getCanonicalPath());
        System.out.println("bootFile.getCanonicalFile() -> " + bootFile.getCanonicalFile());
        System.out.println("bootFile.getParent() -> " + bootFile.getParent());
        System.out.println("bootFile.getParentFile() -> " + bootFile.getParentFile());
        System.out.println("bootFile.isDirectory() -> " + bootFile.isDirectory());
        System.out.println("bootFile.isAbsolute() -> " + bootFile.isAbsolute());
        System.out.println("bootFile.isFile() -> " + bootFile.isFile());
        System.out.println("bootFile.length() -> " + bootFile.length());
        System.out.println("bootFile.getTotalSpace() -> " + bootFile.getTotalSpace());
        System.out.println("bootFile.getFreeSpace() -> " + bootFile.getFreeSpace());
        System.out.println("bootFile.getUsableSpace() -> " + bootFile.getUsableSpace());
        System.out.println("bootFile.list() -> " + Arrays.toString(bootFile.list()));
        System.out.println("bootFile.listFiles() -> " + Arrays.toString(bootFile.listFiles()));
    }

    /**
     * 存储空间，数字转字符串
     *
     * @param space
     * @return String
     */
    private static String convertSpaceNumToStr(long space) {
        long unitSize = 1024;

        double spaceDouble = space;
        String spaceStr = "";

        spaceDouble = convertUnit(spaceDouble, unitSize, SpaceUnitEnum.B);

        return spaceStr;
    }

    private static double convertUnit(double spaceDouble, long unitSize, SpaceUnitEnum spaceUnitEnum) {
        if (spaceDouble >= unitSize) {
            spaceDouble = new BigDecimal(spaceDouble).divide(new BigDecimal(unitSize), BigDecimal.ROUND_HALF_EVEN, 1).doubleValue();
            spaceUnitEnum = spaceUnitEnum.getByCode(spaceUnitEnum.getCode() + 1);
            convertUnit(spaceDouble, unitSize, spaceUnitEnum);
        } else {
            System.out.println("success: " + spaceDouble + spaceUnitEnum);
        }
        return spaceDouble;
    }

    @Test
    public void testConvertUnit() {
        System.out.println(convertUnit(2048, 1024, SpaceUnitEnum.B));
    }

    enum SpaceUnitEnum {
        B(1),
        KB(2),
        MB(3),
        GB(4),
        TB(5),
        UNKNOWN(6),
        ;

        private int code;

        SpaceUnitEnum(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public SpaceUnitEnum getByCode(int code) {
            return Arrays.stream(SpaceUnitEnum.values()).filter(spaceUnitEnum -> spaceUnitEnum.code == code).findFirst().orElse(UNKNOWN);
        }
    }
}
