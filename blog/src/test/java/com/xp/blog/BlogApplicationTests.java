package com.xp.blog;

import com.xp.blog.entity.User;
import com.xp.blog.mapper.CommentMapper;
import com.xp.blog.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApplication.class)
public class BlogApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private CommentMapper commentMapper;
	//记录日志
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void contextLoads() {
		logger.debug("debug......");
		logger.info("info.........");
		logger.error("error......");
		logger.warn("warn........");
	}



	@Test
	public void test1(){
	String[] aa = {"1"};
		String[] split = "111111".split(",");

		//System.out.println(split);

		for (String s : split) {
			System.out.println(s);
		}
	}


	@Test
	public void  test04(){
		String a = "125301036682638982,1253012443147059XE,12530125431442791Q,125301254314428129,12530126431452551F,125301264314535540,12530126431455031N,12530126431456536R,1253012814807397,12530128431480536J,12530128431480720A,,253032211C2101,,153042611C2201,42611c2101,43199701-7,653042611C2201,753042611C1001,,1253300143270647XF,,1253210143156131XB,12532101431561328B,12532101431561475W,153060211C2101,,12532701432385231D,125327014323853111,125327244324165019,43238563753080211C2201,PDY00024X53082211C2101,,12532524432171401U,12532526218038001D,1253252843222012X2,12532528432220154P,12532528432220162J,12532528432220170D,12532528432220189A,43222011-1,43222013853252811C2201,43222014-6,4322201907,,43229568253262211C2101,653262311C2201,,125328014325016930,12532801432501706M,12532801432501773K,43250167-7,43250171-4,43250172-2,43250176-5,,43267229X53293111C2201,43267233753293111C2201,125329014325568032,1253290143255682XL,12532901432556854A,12532901432661136W,43282829053312212C2101";

		String[] list = a.split(",");

		/*List<String> list = new ArrayList<>();
		for (String s : name) {
			list.add(s);
		}*/
		commentMapper.addComment(list, "23230");
	}

}
