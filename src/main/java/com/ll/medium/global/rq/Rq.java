package com.ll.medium.global.rq;

import com.ll.medium.global.rsData.RsData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
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

    public String historyBack(RsData<?> rs) {
        return historyBack(rs.getMsg());
    }

    public String redirectOrBack(String url, RsData<?> rs) {
        if (rs.isFail()) return historyBack(rs);
        return redirect(url, rs);
    }
}
