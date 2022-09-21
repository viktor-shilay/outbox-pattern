package by.shilay.app.consumer.config;

import by.shilay.app.consumer.kafka.KafkaMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public Map<String, Object> consumerConfig(){
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return properties;
    }

    @Bean
    public ConsumerFactory<String, KafkaMessage> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(),
                new JsonDeserializer<>(KafkaMessage.class));
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, KafkaMessage>> eventsKafkaListenerContainerFactory(
            ConsumerFactory<String, KafkaMessage> consumerFactory
    ){
        ConcurrentKafkaListenerContainerFactory<String, KafkaMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setMessageConverter(converter());
        return factory;
    }

    @Bean
    public JsonMessageConverter converter() {
        return new JsonMessageConverter();
    }
}
