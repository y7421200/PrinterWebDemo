package com.example.authentification;

/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;*/

//@Configuration
//@EnableWebSecurity
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/ {
	
	/*@Autowired
	private UserRepository userrepository;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		  .csrf().disable()
          .authorizeRequests()
              .antMatchers("/", "/index.html","/register.html","/user/register","/user/info","/login.html","/library.html",
            		  "/css/**","/img/**","/js/**","/plugins/**")
              .permitAll()
              .anyRequest().authenticated()
              .and()
          .formLogin()
              .loginPage("/user/login")//登陆页面  
              .permitAll()
              .and()
          .logout()
	          .deleteCookies("remove")
	          .invalidateHttpSession(false)
	          .logoutUrl("/user/logout")
	          .logoutSuccessUrl("/");
	}
	
	@Override
	protected void configure(
		AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(new UserDetailsService(){
			@Override
			public UserDetails loadUserByUsername(String username)
				throws UsernameNotFoundException{
					return userrepository.findOne(username);
			}
		});
	}*/
}
