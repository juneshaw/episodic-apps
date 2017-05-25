package com.example.queue;

import com.example.episodicepisodes.Episode;
import com.example.episodicepisodes.EpisodeService;
import com.example.episodicusers.User;
import com.example.episodicusers.UserService;
import com.example.episodicviewings.ViewingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
@ComponentScan("com.example")
public class AmqpListener implements RabbitListenerConfigurer {

    private final ViewingService viewingService;
    private final EpisodeService episodeService;
    private final UserService userService;

    public AmqpListener(ViewingService viewingService,
                        EpisodeService episodeService,
                        UserService userService) {
        assert(viewingService != null);
        assert(episodeService != null);
        assert(userService != null);
        this.viewingService = viewingService;
        this.episodeService = episodeService;
        this.userService = userService;
    }

    @RabbitListener(queues = "#{'${queue}'}") //(queues = "episodic-progress")//    @Transactional
    public void receiveMessage(final MessageEpisodicProgress message) {
        Episode episode = episodeService.read(message.getEpisodeId());
        User user = userService.readOne(message.getUserId());
        if((user != null) && (episode != null)) {
            viewingService.createViewingFromMessage(
                    message,
                    episode);
        }
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(new MappingJackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }
}
