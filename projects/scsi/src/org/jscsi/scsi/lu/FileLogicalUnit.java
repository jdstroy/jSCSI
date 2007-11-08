package org.jscsi.scsi.lu;

import org.apache.log4j.Logger;
import org.jscsi.scsi.protocol.inquiry.InquiryDataRegistry;
import org.jscsi.scsi.protocol.inquiry.StaticInquiryDataRegistry;
import org.jscsi.scsi.protocol.mode.ModePageRegistry;
import org.jscsi.scsi.tasks.file.FileTaskFactory;
import org.jscsi.scsi.tasks.management.DefaultTaskManager;
import org.jscsi.scsi.tasks.management.DefaultTaskSet;
import org.jscsi.scsi.tasks.management.TaskManager;
import org.jscsi.scsi.tasks.management.TaskServiceResponse;
import org.jscsi.scsi.tasks.management.TaskSet;

// TODO: Describe class or interface
public class FileLogicalUnit extends DefaultLogicalUnit
{   
   private static Logger _logger = Logger.getLogger(TaskSet.class);

   private final int NUM_TASK_THREADS = 1;
   
   private final TaskSet taskSet = new DefaultTaskSet(NUM_TASK_THREADS);
   private final TaskManager taskManager = new DefaultTaskManager(NUM_TASK_THREADS, this.taskSet);
   private final InquiryDataRegistry inquiryRegistry = new StaticInquiryDataRegistry();

   public FileLogicalUnit(ModePageRegistry modePageRegistry)
   {
      super();
      this.setTaskSet(taskSet);
      this.setTaskManager(taskManager);
      this.setInquiryDataRegistry(inquiryRegistry);
      
      this.setTaskFactory(new FileTaskFactory())
   }

   public void setModePageRegistry(ModePageRegistry modePageRegistry)
   {
      setTaskFactory(new FileTaskFactory(device, modePageRegistry, getInquiryDataRegistry()));
   }
}