package com.iu.base.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.iu.base.board.notice.NoticeDAO;
import com.iu.base.board.notice.NoticeVO;
import com.iu.base.member.MemberDAO;
import com.iu.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestSchedule {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Scheduled(cron = "*/10 * * * * *")
	public void test() throws Exception {
//		log.error("===========반복중===========");
//		
//		List<MemberVO> ar = memberDAO.allUserName();
//		for(MemberVO memberVO : ar) {
//			memberVO.getUserName();
//			log.error(memberVO.getUserName());
//		}
//		
//		memberDAO.setEnabled();
		
		List<MemberVO> ar = memberDAO.getBirth();
		
		StringBuffer sb = new StringBuffer();
		sb.append("오늘은 ");
		
		for(MemberVO memberVO:ar) {
			sb.append(memberVO.getUserName());
			sb.append(", ");
		}
		
		sb.append(" 생일입니다.");
		
		NoticeVO noticeVO = new NoticeVO();
		
		noticeVO.setTitle("생일 축하");
		noticeVO.setWriter("사장님");
		noticeVO.setContents(sb.toString());
		
		noticeDAO.setInsert(noticeVO);
		
	}
	
}
