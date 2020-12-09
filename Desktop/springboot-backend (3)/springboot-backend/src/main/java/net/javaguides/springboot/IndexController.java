package net.javaguides.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;

@RestController
@Controller
public class IndexController {
    @Autowired
    private JavaMailSender javaMailSender;

    
    
    @GetMapping("/{id}")
    public String index(@PathVariable String id) throws MessagingException, UnsupportedEncodingException {
 
        String to = id;
        String from = "estein92@forcs.com";
        String subject = "비밀번호 찾기";
        
        String back=id;
        String url="http://localhost:3000/updateuser_af/"+back;
        

        StringBuilder body = new StringBuilder();
        body.append("<html> <body><h1>비밀번호 초기화</h1>");
        body.append("<div>밑에 링크를 클릭하시면 초기화 페이지로 연결됩니다.</div> </body></html>");
        body.append(url);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");

        mimeMessageHelper.setFrom(from,"forcs");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body.toString(), true);

        javaMailSender.send(message);

        return "    ";
    }

    

}