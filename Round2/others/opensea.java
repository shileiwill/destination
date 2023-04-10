opensea.java

/**
OpenSea is releasing a new feature called "What are my friends favoriting?". The feature lists a set of assets that your friends have favorited and you have not already favorited, in order of most favorited to least favorited.



To help with this, we have the following 2 APIs / helper methods. Complete the function that takes in a person and returns the assets that the person's friends are favoriting.

// Returns a list of assets favorited by a Person
List<Asset> getFavorites(Person person);

// Returns a list of friends of a Person.
List<Person> getFriends(Person person);

// TODO: Return a list of assets that a Person's friends have favorited (excluding the person's own favorites), sorted by most favorites to least favorites
List<Asset> getRecommendedAssets(Person person);

Instead of your friends, what about your social circle? A social circle of a person with degree 2 is defined as the person's friends and their friends. (Customize it such that we can specify the degree, or the layers deep of how many friends)
List<Person> getSocialCircle(Person person, int degree)
What if we wanted to s‍‍‍‍ort the list weighted by the degree of your friends? Assets favorited by a friend with a lower degree should appear earlier on the list.

*/

List<Asset> getRecommendedAssets(Person person) {
	// get all my friends
	List<Person> friends = getFriends(person);
	List<Asset> myAssets = getFavorites(person);

	Set<Asset> set = new HashSet<>();

	for (Person p : friends) {
		List<Asset> assets = getFavorites(p);

		for (Asset a : assets) {
			// convert list to set
			if (!myAssets.contains(a.name)) {
				set.add(a);
			}
		}
	}

	List<Asset> list = new ArrayList<>();
	for (Asset a : set) {
		list.add(a);
	}

	Collections.sort(list, new Comparator<Asset>(){
		public int compare(Asset a1, Asset a2) {
			return a2.fav - a1.fav;
		}
	});

	return set;
}

class AssetWithDegree {
	Asset a;
	int degree;
}

List<Asset> getRecommendedAssets(Person person, int degree) {
	// get all friends within degree
	List<Person> friends = getSocialCircle(person, degree)
	List<Asset> myAssets = getFavorites(person);

	for (Person p : friends) {
		
	}
}
