package com.jagrosh.jmusicbot.commands.dj;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import com.jagrosh.jmusicbot.commands.DJCommand;

public class NightCoreCmd extends DJCommand {
    public NightCoreCmd(Bot bot) {
        super(bot);
        this.name = "nightcore";
        this.help = "apply nightcore to the current track";
        this.aliases = bot.getConfig().getAliases(this.name);
        this.beListening = false;
        this.bePlaying = true;
    }

    @Override
    public void doCommand(CommandEvent event) {
        AudioHandler handler = (AudioHandler) event.getGuild().getAudioManager().getSendingHandler();

        if(!handler.getBassboostState()) {
            handler.enableBassboost(true);
            event.replySuccess("Enabled `bassboost`!");
        } else {
            handler.enableBassboost(false);
            event.replySuccess("Disabled `bassboost`!");
        }

    }
}
