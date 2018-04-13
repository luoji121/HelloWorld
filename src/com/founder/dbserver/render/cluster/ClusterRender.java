package com.founder.dbserver.render.cluster;

import com.founder.dbserver.bean.DynamicCluster;
import com.founder.dbserver.bean.SimplePoint;
import com.founder.dbserver.dto.QueryClusterDto;
import com.founder.dbserver.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangjf
 * @version V1.0
 * @Description: 聚合分析
 * @date 2017/11/6
 */
@Component
public class ClusterRender {
    @Autowired(required = false)
    private CacheService sc;
    private double scaleX;
    private double scaleY;

    /**
     * 数据聚合处理
     *
     * @param qcd
     */
    public void assignPointsClusters(QueryClusterDto qcd) {
        Map<String, DynamicCluster> map = new HashMap<String, DynamicCluster>();
        int diameter = qcd.getDiameter();
        scaleX = ((qcd.getMaxX() - qcd.getMinX()) * 3600) / qcd.getWidth();
        scaleY = ((qcd.getMaxY() - qcd.getMinY()) * 3600) / qcd.getHeight();

        List<SimplePoint> points = sc.findSimplePoint(qcd);
        for (int i = 0; i < points.size(); i++) {
            SimplePoint sp = points.get(i);
            int sx = getScrX(sp.getX(), qcd.getMinX());
            int sy = getScrY(sp.getY(), qcd.getMaxY());
            int cx = sx / diameter;
            int cy = sy / diameter;
            String ci = String.valueOf(cx) + String.valueOf(cy);
            boolean haskey = map.containsKey(ci);
            if (haskey) {
                DynamicCluster cluster = map.get(ci);
                cluster.setX((cluster.getX() + sp.getX()) / 2);
                cluster.setY((cluster.getY() + sp.getX()) / 2);
                cluster.setSx((cluster.getSx() + sx) / 2);
                cluster.setSy((cluster.getSy() + sy) / 2);
                cluster.setCount(cluster.getCount() + 1);
                cluster.addItem(sp);

            } else {
                DynamicCluster new_cluster = new DynamicCluster();
                new_cluster.setX(sp.getX());
                new_cluster.setY(sp.getY());
                new_cluster.setSx(sx);
                new_cluster.setSy(sy);
                new_cluster.setCx(cx);
                new_cluster.setCy(cy);
                new_cluster.setCount(1);
                new_cluster.addItem(sp);
                map.put(ci, new_cluster);

            }

        }
        mergeClusters(map, diameter);
        for (Map.Entry<String, DynamicCluster> entry : map.entrySet()) {
            System.out.println("key=" + entry.getKey());
            System.out.println("x=" + entry.getValue().getX() + "    y=" + entry.getValue().getY());
        }


    }

    /**
     * 判断相邻的两个聚合圆是否也需要聚合
     */
    public void mergeClusters(Map<String, DynamicCluster> map, int diameter) {
        for (Map.Entry<String, DynamicCluster> entry : map.entrySet()) {
            DynamicCluster dc = entry.getValue();
            if (dc.getCount() == 0) {
                continue;
            }
            //8个方向寻找邻近可聚合的圆 Search all immediately adjacent clusters.
            searchAndMerge(map, diameter, dc, 1, 0);
            searchAndMerge(map, diameter, dc, -1, 0);
            searchAndMerge(map, diameter, dc, 0, 1);
            searchAndMerge(map, diameter, dc, 0, -1);
            searchAndMerge(map, diameter, dc, 1, 1);
            searchAndMerge(map, diameter, dc, 1, -1);
            searchAndMerge(map, diameter, dc, -1, 1);
            searchAndMerge(map, diameter, dc, -1, -1);
        }
    }

    /**
     * 执行聚合
     *
     * @param dc
     * @param ox
     * @param oy
     */
    public void searchAndMerge(Map<String, DynamicCluster> map, int diameter, DynamicCluster dc, int ox, int oy) {
        final int cx = dc.getCx() + ox;
        final int cy = dc.getCy() + oy;
        final String ci = String.valueOf(cx) + String.valueOf(cy);
        final boolean haskey = map.containsKey(ci);
        if (haskey) {
            DynamicCluster cluster = map.get(ci);
            if (cluster.getCount() > 0) {
                int dx = cluster.getSx() - dc.getSx();
                int dy = cluster.getSy() - dc.getSy();
                double dd = Math.sqrt(dx * dx + dy * dy);
                if (dd < diameter) {
                    System.out.println("发生两圆聚合:" + dc.getX() + "," + dc.getY() + "和" + cluster.getX() + "," + cluster.getY());
                    merge(dc, cluster);
                }
            }

        }


    }

    /**
     * 两聚合圆进行聚合 并靠近点多的圆
     *
     * @param fdc
     * @param sdc
     */
    public void merge(DynamicCluster fdc, DynamicCluster sdc) {
        int count = fdc.getCount() + sdc.getCount();
        fdc.setSx((fdc.getCount() * fdc.getSx() + sdc.getCount() * sdc.getSx()) / count);
        fdc.setSy((fdc.getCount() * fdc.getSy() + sdc.getCount() * sdc.getSy()) / count);
        fdc.setX((fdc.getCount() * fdc.getX() + sdc.getCount() * sdc.getX()) / count);
        fdc.setY((fdc.getCount() * fdc.getY() + sdc.getCount() * sdc.getY()) / count);
        fdc.addListItem(sdc.getPoints());
        fdc.setCount(count);
        sdc.setCount(0);
        sdc.initPoints();
    }

    private int getScrX(double x, double minX) {
        return (int) ((x - minX) * 3600 / scaleX);
    }

    private int getScrY(double y, double maxY) {
        return (int) ((maxY - y) * 3600 / scaleY);
    }

}
