
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.NewspaperSubscription;

@Component
@Transactional
public class NewspaperSubscriptionToStringConverter implements Converter<NewspaperSubscription, String> {

	@Override
	public String convert(final NewspaperSubscription subscription) {
		String result;

		if (subscription == null)
			result = null;
		else
			result = String.valueOf(subscription.getId());
		return result;
	}

}
