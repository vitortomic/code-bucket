/**
	 * filters provided users list by provided user search model properties
	 * filters by roles and business partners
	 * @param users
	 * @param model
	 * @return
	 */
	private List<User> filterUsersByCriteriaModel(List<User> users, UserSearchCriteriaPostModel model){
		Stream<User> usersStream = users.stream();
		if(model.getUserRole() != null){
			//filter users whose roles list contains provided role
			usersStream = usersStream.filter(u ->
			u.getRoles().stream().map(r -> r.getUserRole())
			.anyMatch(roleEnum -> roleEnum.equals(model.getUserRole())));
		}
		if(model.getBusinessParnerName() != null){
			//filter users whose business partner list contains the provided business partner
			usersStream = usersStream.filter(u ->
			u.getBusinessPartners().stream().map(bp -> bp.getName())
			.anyMatch(name -> name.equals(model.getBusinessParnerName())));
		}
		return usersStream.collect(Collectors.toList());
	}