package com.jagrosh.jmusicbot.commands.music;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import com.jagrosh.jmusicbot.audio.QueuedTrack;
import com.jagrosh.jmusicbot.commands.MusicCommand;
import sun.misc.Queue;

public class RemoveSameCmd extends MusicCommand {

    public RemoveSameCmd(Bot bot) {
        super(bot);
        this.name = "RemoveSame";
        this.arguments = "<queue position>";
        this.help = "remove same tracks in queue";
    }

    @Override
    public void doCommand(CommandEvent event) {
        AudioHandler handler = (AudioHandler)event.getGuild().getAudioManager().getSendingHandler();
        QueuedTrack qtrack = handler.getQueue().getList().get(Integer.parseInt(event.getArgs()));

        if(handler.getQueue().isEmpty())  event.replyError("Queue is null");

        if(event.getArgs().equals(null)) event.replyError("Please, include track index");


        qtrack.getIdentifier();
    }
}
