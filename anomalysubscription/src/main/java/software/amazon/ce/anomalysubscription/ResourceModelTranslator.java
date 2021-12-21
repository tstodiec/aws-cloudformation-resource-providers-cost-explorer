package software.amazon.ce.anomalysubscription;

import lombok.experimental.UtilityClass;
import org.apache.commons.collections.CollectionUtils;
import software.amazon.awssdk.services.costexplorer.model.ResourceTag;
import software.amazon.awssdk.services.costexplorer.model.Subscriber;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@UtilityClass
public class ResourceModelTranslator {
    public static List<software.amazon.ce.anomalysubscription.Subscriber> toSubscribers(List<Subscriber> subscribers) {
        if (CollectionUtils.isEmpty(subscribers)) {
            return null;
        }

        return subscribers.stream().map(
                subscriber -> software.amazon.ce.anomalysubscription.Subscriber.builder()
                    .address(subscriber.address())
                    .status(subscriber.status() != null ? subscriber.status().toString() : null)
                    .type(subscriber.type().toString())
                    .build())
                .collect(Collectors.toList());
    }

    public static List<Subscriber> toSDKSubscribers(List<software.amazon.ce.anomalysubscription.Subscriber> subscribers) {
        if (CollectionUtils.isEmpty(subscribers)) {
            return null;
        }

        return subscribers.stream().filter(Objects::nonNull).map(
                subscriber -> Subscriber.builder()
                    .address(subscriber.getAddress())
                    .status(subscriber.getStatus() != null ? subscriber.getStatus().toString() : null)
                    .type(subscriber.getType() != null ? subscriber.getType().toString() : null)
                    .build())
                .collect(Collectors.toList());
    }

    public static List<ResourceTag> toSDKResourceTags(List<software.amazon.ce.anomalysubscription.ResourceTag> resourceTags) {
        if (CollectionUtils.isEmpty(resourceTags)) {
            return null;
        }

        return resourceTags.stream().filter(Objects::nonNull).map(
                resourceTag -> ResourceTag.builder()
                        .key(resourceTag.getKey())
                        .value(resourceTag.getValue())
                        .build())
                .collect(Collectors.toList());
    }
}
