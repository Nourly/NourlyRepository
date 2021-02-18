package dataStructures.recurison;

/**
 * @author 69035
 * @date 2021/2/18
 */
public class MiGong {

    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1表示墙
        //上下全部设置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;

        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        //使用递归回溯找路
        setWay(map,1,1);
        System.out.println();
        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 说明: 0 表示没有走过，1表示为墙，2表示可以走，3表示该点已经走过，但走不通
     * 走迷宫的时候需要确定一个策略（方向） 方向为:下->右->上->左，如果走不通再回溯
     *
     * @param map 地图
     * @param i   开始横坐标
     * @param j   开始纵坐标
     * @return
     */
    public static boolean setWay(int[][] map, int i, int j) {
        //终点为6，5 为2的时候退出
        if (map[6][5] == 2) {
            return true;
        } else {
            if(map[i][j] == 0){
                //移动方案:下->右->上->左
                //假定该点可以走通的
                map[i][j] = 2;
                if(setWay(map,i+1,j)){
                    //向下走
                    return true;
                }else if(setWay(map,i,j+1)){
                    return true;
                }else if(setWay(map,i-1,j)){
                    return true;
                }else if(setWay(map,i,j-1)){
                    return true;
                }else{
                    //说明该点无法移动，是死路
                    map[i][j] = 3;
                    return false;
                }
            }else{
                //如果该点不为0，map可能是1，2，3
                return false;
            }
        }
    }
}
