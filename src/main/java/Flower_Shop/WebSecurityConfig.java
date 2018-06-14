package Flower_Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import Flower_Shop.Repositories.StaffRepo;
import Flower_Shop.entities.Staff;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	StaffRepo SR;
	
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/", "/style.css", "/style2.css", "/stock1.png", "/stock2.png", "/stage2.png", "/stage3.png", "/shop/**", "/admin/shop/getName").permitAll()
		.antMatchers("/admin/**").hasAnyRole("ADMIN", "MANAGER", "USER")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.permitAll();
		
		http.csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		for(Staff staff : SR.findAll()){
			String position = tranlsate(staff.getPosition());
			auth.inMemoryAuthentication().withUser(String.valueOf(staff.getId())).password(staff.getPassword()).roles(position);
		}
	}
	
	private String tranlsate(String position){
		if(position.equals("Administrator"))
			return "ADMIN";
		else if (position.equals("Manager"))
			return "MANAGER";
		else
			return "USER";
	}
}
