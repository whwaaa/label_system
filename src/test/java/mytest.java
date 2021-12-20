import com.jumaojiang.mapper.LabelMapper;
import com.jumaojiang.pojo.Label;
import com.jumaojiang.service.EditLabelServiceImp;
import com.jumaojiang.utils.FileZipUtils;
import com.jumaojiang.vo.LabelListVO;
import com.jumaojiang.vo.LabelMapVO;
import com.jumaojiang.vo.LabelVO;
import com.jumaojiang.vo.ListVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * api
 *
 * @author wuhanwei
 * @version 1.0
 * @date 2021/10/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class mytest {

    @Resource
    private LabelMapper labelMapper;

    @Resource
    private EditLabelServiceImp editLabelServiceImp;

    @Test
    public void test002(){
        Label label = new Label();
        label.setSize("S,L");
        label.setKuanHao("abcde");
        label.setTypeName("abcd");
        editLabelServiceImp.insert(label);
    }

    @Test
    public void test001(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse("2021-12-19");
        } catch (ParseException e) { }
        ListVo listVo = new ListVo();
        listVo.setCreateTime(date);
        listVo.setTypeName("a");
        listVo.setKuanHao("a");
        listVo.setBlank1("0");
        LabelVO list = editLabelServiceImp.list(0, 0, listVo);
        System.out.println(list);
//        editLabelServiceImp.updateBlank(list);
    }


    public static void main(String[] args) {

        String dir = "C:\\Users\\w\\Desktop\\saveDir";
        testttt(false);
    }

    public static void testttt(boolean... isDigui){
        if(isDigui.length!=0 && isDigui[0]){
            System.out.println("递归调用");
//            testttt(true);
        }else{
            System.out.println("非递归调用");
        }

    }


}
