package info.luckydog.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeUtil {

    public static void main(String[] args) {
        List<Department> depList = getDepList();

        List<List<DepartmentDTO>> result = new ArrayList<>();
        Map<Integer, List<DepartmentDTO>> map = new HashMap<>();
        DepartmentDTO departmentDTO;
        for (Department department : depList) {
            departmentDTO = new DepartmentDTO();
            departmentDTO.setId("D" + department.getId());
            departmentDTO.setOriginId(department.getId() + "");
            departmentDTO.setParentId("D" + department.getParentId());
            departmentDTO.setName(department.getName());
            departmentDTO.setLeafFlag(department.getLeafFlag());
            departmentDTO.setReserve1(department.getRouter() + "-" + department.getId());
            departmentDTO.setReserve2(department.getNameRouter() + "-" + department.getName());

            if (!map.containsKey(department.getLevel())) {
                map.put(department.getLevel(), new ArrayList<>());
                result.add(map.get(department.getLevel()));
            }
            map.get(department.getLevel()).add(departmentDTO);
        }
        result.forEach(System.out::println);
    }

    private static List<Department> getDepList() {

        String depListStr = "[\n" +
                "    {\n" +
                "        \"router\": \"0\",\n" +
                "        \"leafFlag\": 1,\n" +
                "        \"level\": 1,\n" +
                "        \"name\": \"技术开发部\",\n" +
                "        \"namerouter\": \"总部\",\n" +
                "        \"id\": 110364,\n" +
                "        \"parentid\": 0\n" +
                "    },\n" +
                "    {\n" +
                "        \"router\": \"0\",\n" +
                "        \"leafFlag\": 1,\n" +
                "        \"level\": 1,\n" +
                "        \"name\": \"财务行政中心\",\n" +
                "        \"namerouter\": \"总部\",\n" +
                "        \"id\": 110298,\n" +
                "        \"parentid\": 0\n" +
                "    },\n" +
                "    {\n" +
                "        \"router\": \"0\",\n" +
                "        \"leafFlag\": 0,\n" +
                "        \"level\": 1,\n" +
                "        \"name\": \"运营管理中心\",\n" +
                "        \"namerouter\": \"总部\",\n" +
                "        \"id\": 110309,\n" +
                "        \"parentid\": 0\n" +
                "    },\n" +
                "    {\n" +
                "        \"router\": \"0-110309-110331-110199\",\n" +
                "        \"leafFlag\": 1,\n" +
                "        \"level\": 2,\n" +
                "        \"name\": \"业务中心\",\n" +
                "        \"namerouter\": \"总部-运营管理中心-河南事业部-新乡东站店\",\n" +
                "        \"id\": 110210,\n" +
                "        \"parentid\": 110199\n" +
                "    },\n" +
                "    {\n" +
                "        \"router\": \"0-110309-110331-110199\",\n" +
                "        \"leafFlag\": 1,\n" +
                "        \"level\": 2,\n" +
                "        \"name\": \"运营中心\",\n" +
                "        \"namerouter\": \"总部-运营管理中心-河南事业部-新乡东站店\",\n" +
                "        \"id\": 110221,\n" +
                "        \"parentid\": 110199\n" +
                "    },\n" +
                "    {\n" +
                "        \"router\": \"0-110309-110243\",\n" +
                "        \"leafFlag\": 1,\n" +
                "        \"level\": 3,\n" +
                "        \"name\": \"胡永仙房源\",\n" +
                "        \"namerouter\": \"总部-运营管理中心-上海发展部\",\n" +
                "        \"id\": 108780,\n" +
                "        \"parentid\": 110243\n" +
                "    },\n" +
                "    {\n" +
                "        \"router\": \"0-110309-110243\",\n" +
                "        \"leafFlag\": 1,\n" +
                "        \"level\": 3,\n" +
                "        \"name\": \"原自居房源-石丽娟\",\n" +
                "        \"namerouter\": \"总部-运营管理中心-上海发展部\",\n" +
                "        \"id\": 108868,\n" +
                "        \"parentid\": 110243\n" +
                "    },\n" +
                "    {\n" +
                "        \"router\": \"0-110309-110243\",\n" +
                "        \"leafFlag\": 1,\n" +
                "        \"level\": 3,\n" +
                "        \"name\": \"自在居泗泾店\",\n" +
                "        \"namerouter\": \"总部-运营管理中心-上海发展部\",\n" +
                "        \"id\": 110254,\n" +
                "        \"parentid\": 110243\n" +
                "    },\n" +
                "    {\n" +
                "        \"router\": \"0-110309-110243\",\n" +
                "        \"leafFlag\": 0,\n" +
                "        \"level\": 3,\n" +
                "        \"name\": \"自在居北蔡店\",\n" +
                "        \"namerouter\": \"总部-运营管理中心-上海发展部\",\n" +
                "        \"id\": 110265,\n" +
                "        \"parentid\": 110243\n" +
                "    },\n" +
                "    {\n" +
                "        \"router\": \"0-110309\",\n" +
                "        \"leafFlag\": 0,\n" +
                "        \"level\": 2,\n" +
                "        \"name\": \"上海发展部\",\n" +
                "        \"namerouter\": \"总部-运营管理中心\",\n" +
                "        \"id\": 110243,\n" +
                "        \"parentid\": 110309\n" +
                "    },\n" +
                "    {\n" +
                "        \"router\": \"0-110309\",\n" +
                "        \"leafFlag\": 0,\n" +
                "        \"level\": 2,\n" +
                "        \"name\": \"杭州事业发展部\",\n" +
                "        \"namerouter\": \"总部-运营管理中心\",\n" +
                "        \"id\": 110320,\n" +
                "        \"parentid\": 110309\n" +
                "    },\n" +
                "    {\n" +
                "        \"router\": \"0-110309\",\n" +
                "        \"leafFlag\": 0,\n" +
                "        \"level\": 2,\n" +
                "        \"name\": \"河南事业部\",\n" +
                "        \"namerouter\": \"总部-运营管理中心\",\n" +
                "        \"id\": 110331,\n" +
                "        \"parentid\": 110309\n" +
                "    },\n" +
                "    {\n" +
                "        \"router\": \"0-110309\",\n" +
                "        \"leafFlag\": 1,\n" +
                "        \"level\": 2,\n" +
                "        \"name\": \"北京事业部\",\n" +
                "        \"namerouter\": \"总部-运营管理中心\",\n" +
                "        \"id\": 110375,\n" +
                "        \"parentid\": 110309\n" +
                "    },\n" +
                "    {\n" +
                "        \"router\": \"0-110309-110320\",\n" +
                "        \"leafFlag\": 1,\n" +
                "        \"level\": 3,\n" +
                "        \"name\": \"杭州万创国际\",\n" +
                "        \"namerouter\": \"总部-运营管理中心-杭州事业发展部\",\n" +
                "        \"id\": 108791,\n" +
                "        \"parentid\": 110320\n" +
                "    },\n" +
                "    {\n" +
                "        \"router\": \"0-110309-110331\",\n" +
                "        \"leafFlag\": 0,\n" +
                "        \"level\": 3,\n" +
                "        \"name\": \"洛阳E395店\",\n" +
                "        \"namerouter\": \"总部-运营管理中心-河南事业部\",\n" +
                "        \"id\": 110342,\n" +
                "        \"parentid\": 110331\n" +
                "    },\n" +
                "    {\n" +
                "        \"router\": \"0-110309-110331\",\n" +
                "        \"leafFlag\": 0,\n" +
                "        \"level\": 3,\n" +
                "        \"name\": \"新乡东站店\",\n" +
                "        \"namerouter\": \"总部-运营管理中心-河南事业部\",\n" +
                "        \"id\": 110199,\n" +
                "        \"parentid\": 110331\n" +
                "    },\n" +
                "    {\n" +
                "        \"router\": \"0-110309-110331-110342\",\n" +
                "        \"leafFlag\": 1,\n" +
                "        \"level\": 4,\n" +
                "        \"name\": \"服务中心\",\n" +
                "        \"namerouter\": \"总部-运营管理中心-河南事业部-洛阳E395店\",\n" +
                "        \"id\": 110353,\n" +
                "        \"parentid\": 110342\n" +
                "    }\n" +
                "]";

        return JsonUtil.jsonToList(depListStr, Department.class);
    }
}

@Data
class Department {
    private Long id;
    private Long parentId;
    private String name;
    private Integer leafFlag;
    private String router;
    private String nameRouter;
    private Integer level;
}

@Data
class DepartmentDTO {
    private String id;
    private String originId;
    private String parentId;
    private String name;
    private Integer leafFlag;
    private String reserve1;
    private String reserve2;
}
