
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.NewspaperSubscriptionRepository;
import domain.NewspaperSubscription;

@Component
@Transactional
public class StringToNewspaperSubscriptionConverter implements Converter<String, NewspaperSubscription> {

	@Autowired
	NewspaperSubscriptionRepository	newspaperSubscriptionRepository;


	@Override
	public NewspaperSubscription convert(final String text) {
		NewspaperSubscription result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.newspaperSubscriptionRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
