package com.project.triport.service;

import com.project.triport.entity.Member;
import com.project.triport.repository.MemberRepository;
import com.project.triport.requestDto.MailRequestDto;
import com.project.triport.responseDto.MailResponseDto;
import com.project.triport.util.MailHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@AllArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
//    private @Value("${spring.mail.username}") String fromMail;

    // 임시 비밀번호 발송
    @Transactional
    public MailResponseDto sendTempPwd(MailRequestDto mailRequestDto) {

        Member member = memberRepository.findByEmail(mailRequestDto.getEmail())
                .orElseThrow(() -> new RuntimeException("입력하신 이메일로 가입된 사용자가 없습니다."));

        String tmpPwd = generateTempPwd();
        member.updateTmpPassword(passwordEncoder, tmpPwd);

        try {
            MailHandler mailHandler = new MailHandler(mailSender);

            // 받는 사람
            mailHandler.setTo(member.getEmail());
            mailHandler.setFrom("triport.helpdesk@gmail.com");
            // 제목
            mailHandler.setSubject("[Triport] 회원님의 임시 비밀번호를 확인해 주세요.");
            // HTML Layout
            // TODO: 별도 method로 분리
            String htmlContent = "<img src='cid:tripper_with_logo' style='width:300px'> <br> <br>" +
                    "<p> 안녕하세요, 여행의 설레임 Triport✈️ 입니다! <br>" +
                    "아래의 임시 비밀번호로 로그인하여 주시기 바랍니다. <br> <br>" +
                    "임시 비밀번호: " + tmpPwd + "<br> <br>" +
                    "로그인 후, 반드시 비밀번호를 변경하여 주세요.😊 <br>" +
                    "이용해주셔서 감사합니다~!♥️ <p> <br> <br>";
            mailHandler.setText(htmlContent, true);
            // 이미지 삽입
            mailHandler.setInline("tripper_with_logo", "static/tripper_with_logo.png");

            mailHandler.send();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new MailResponseDto("회원님의 이메일로 임시 비밀번호를 발송하였습니다.");
    }

    // 랜덤 영문자+숫자 생성
    public String generateTempPwd() {

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 12;

        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return (generatedString);
    }

}
