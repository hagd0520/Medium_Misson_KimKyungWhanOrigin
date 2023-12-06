package com.ll.medium.global.rq;

import com.ll.medium.global.rsData.RsData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
@Getter
@RequiredArgsConstructor
public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;

    public String redirect(String path, RsData<?> rs) {
        return redirect(path, rs.getMsg());
    }

    private String redirect(String path, String msg) {
            return "redirect:" + path;
    }

    public String historyBack(String msg) {
        resp.setStatus(400);
        req.setAttribute("msg", msg);

        return "global/js";
    }
}
