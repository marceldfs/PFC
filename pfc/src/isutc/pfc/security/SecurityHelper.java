package isutc.pfc.security;


import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;


/**
 * Provides static convenience methods for use by Presentation Layer when using Security Module.
 */
public abstract class SecurityHelper implements Authentication
{
	//================== class variables ====================
	
		
	//================== public behaviour ===================
	
	 /**
	  * Retrieve the User reprenting the logged-in.
      * NOTE: this method does NOT 'login' the user - logging in is performed automatically by Spring Security
      *
      * @return User representing logged-in user
      */
	public static User getUser()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		return (User)auth.getPrincipal();
	}
	
	/**
	  * Retrieve the User reprenting the logged-in.
     * NOTE: this method does NOT 'login' the user - logging in is performed automatically by Spring Security
     *
     * @return User representing logged-in user
     */
	public static Authentication getAuth()
	{
		return  SecurityContextHolder.getContext().getAuthentication();
	}
   
	
}
