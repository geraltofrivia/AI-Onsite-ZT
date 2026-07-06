// https://github.com/adityachandelgit/BookLore/tree/f3f59155edbe53b3ba42d314467f980f09e48c8b/booklore-api/src/main/java/com/adityachandel/booklore/config/security/JwtAuthenticationFilter.java#L38-L51
public class TempClass {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = getJwtFromRequest(request);
        if (token != null && jwtUtils.validateToken(token)) {
            Long userId = jwtUtils.extractUserId(token);
            BookLoreUserEntity bookLoreUserEntity = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            BookLoreUser bookLoreUser = bookLoreUserTransformer.toDTO(bookLoreUserEntity);
            List<GrantedAuthority> authorities = getAuthorities(bookLoreUserEntity.getPermissions());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(bookLoreUser, null, authorities);
            authentication.setDetails(new UserAuthenticationDetails(request, bookLoreUser.getId()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

}