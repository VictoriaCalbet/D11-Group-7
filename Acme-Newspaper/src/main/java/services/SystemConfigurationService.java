
package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SystemConfigurationRepository;
import domain.Administrator;
import domain.Article;
import domain.Chirp;
import domain.Newspaper;
import domain.SystemConfiguration;

@Service
@Transactional
public class SystemConfigurationService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private SystemConfigurationRepository	systemConfigurationRepository;

	@Autowired
	private AdministratorService			administratorService;

	@Autowired
	private ArticleService					articleService;

	@Autowired
	private NewspaperService				newspaperService;

	@Autowired
	private ChirpService					chirpService;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public SystemConfigurationService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public SystemConfiguration create() {
		SystemConfiguration result;

		result = new SystemConfiguration();
		result.setTabooWords(new HashSet<String>());

		return result;
	}

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public SystemConfiguration save(final SystemConfiguration systemConfiguration) {
		Assert.notNull(systemConfiguration);
		SystemConfiguration result;
		result = this.systemConfigurationRepository.save(systemConfiguration);
		return result;
	}

	public SystemConfiguration saveFromCreate(final SystemConfiguration systemConfiguration) {
		Assert.notNull(systemConfiguration);

		this.checkAdmin();

		SystemConfiguration result;

		result = this.save(systemConfiguration);

		return result;
	}

	public SystemConfiguration saveFromEdit(final SystemConfiguration systemConfiguration) {
		Assert.notNull(systemConfiguration);

		this.checkAdmin();

		SystemConfiguration result;

		result = this.save(systemConfiguration);

		return result;
	}

	public Collection<SystemConfiguration> findAll() {
		Collection<SystemConfiguration> result = null;
		result = this.systemConfigurationRepository.findAll();
		return result;
	}

	public SystemConfiguration findOne(final int systemConfigurationId) {
		SystemConfiguration result = null;
		result = this.systemConfigurationRepository.findOne(systemConfigurationId);
		return result;
	}

	public SystemConfiguration findMain() {
		SystemConfiguration result;
		this.checkAdmin();
		result = this.findAll().iterator().next();

		return result;
	}

	// Other business methods -------------------------------------------------

	public Collection<Newspaper> getTabooNewspapers() {
		final Collection<Newspaper> result = new HashSet<>();
		SystemConfiguration systemConfiguration;

		systemConfiguration = this.findMain();

		for (final String tabooWord : systemConfiguration.getTabooWords())
			result.addAll(this.newspaperService.getTabooNewspapers(tabooWord));

		return result;
	}

	public Collection<Article> getTabooArticles() {
		final Collection<Article> result = new HashSet<>();
		SystemConfiguration systemConfiguration;

		systemConfiguration = this.findMain();

		for (final String tabooWord : systemConfiguration.getTabooWords())
			result.addAll(this.articleService.getTabooArticles(tabooWord));

		return result;
	}

	public Collection<Chirp> getTabooChirps() {
		final Collection<Chirp> result = new HashSet<>();
		SystemConfiguration systemConfiguration;

		systemConfiguration = this.findMain();

		for (final String tabooWord : systemConfiguration.getTabooWords())
			result.addAll(this.chirpService.getTabooChirps(tabooWord));

		return result;
	}
	//Auxiliar method
	private void checkAdmin() {
		Administrator actor;
		actor = this.administratorService.findByPrincipal();
		Assert.notNull(actor);
		String authority;
		authority = actor.getUserAccount().getAuthorities().iterator().next().getAuthority();
		Assert.isTrue(authority.equals("ADMIN"));
	}
	//-------------------------------------------
	//	public Collection<Article> getTabooArticles() {
	//
	//		final EntityManagerFactory factory = Persistence.createEntityManagerFactory("Acme-Newspaper");
	//
	//		final EntityManager em = factory.createEntityManager();
	//
	//		final FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
	//
	//		em.getTransaction().begin();
	//
	//		String regexp = "";
	//
	//		for (final String spamWord : this.findMain().getTabooWords())
	//
	//			regexp += spamWord + "|";
	//
	//		final QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Article.class).get();
	//
	//		final org.apache.lucene.search.Query luceneQuery = qb.keyword().onFields("title", "summary", "body").matching(regexp).createQuery();
	//
	//		final javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Article.class);
	//
	//		final List result = jpaQuery.getResultList();
	//
	//		em.getTransaction().commit();
	//
	//		em.close();
	//
	//		return result;
	//
	//	}
}
