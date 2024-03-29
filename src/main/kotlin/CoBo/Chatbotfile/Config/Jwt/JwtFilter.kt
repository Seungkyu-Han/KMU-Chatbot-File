package CoBo.Chatbotfile.Config.Jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
@RequiredArgsConstructor
class JwtFilter(
    private val jwtTokenProvider: JwtTokenProvider): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorization = request.getHeader(HttpHeaders.AUTHORIZATION)

        if(authorization != null && authorization.startsWith("Bearer ")){
            val token = authorization.split(" ")[1]
            if(jwtTokenProvider.isAccessToken(token) && jwtTokenProvider.isActiveState(token)){
                val userId = jwtTokenProvider.getUserId(token)
                SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(userId, null, listOf(
                    SimpleGrantedAuthority(jwtTokenProvider.getUserRole(token))))
            }
        }

        filterChain.doFilter(request, response)
    }


}