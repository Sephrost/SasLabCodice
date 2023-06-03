package catering.businesslogic.user;

public class UserManager {
    private User currentUser;

    public void fakeLogin(String username) 
    {
        this.currentUser = User.loadUser(username);
    }

		public void loginWithUsername(String username){
			this.currentUser = User.loadUser(username);
		}

    public User getCurrentUser() {
        return this.currentUser;
    }
}
