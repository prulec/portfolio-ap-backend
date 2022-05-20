import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { PortfolioService } from 'src/app/services/portfolio.service';

@Component({
  selector: 'app-delete-item',
  templateUrl: './delete-item.component.html',
  styleUrls: ['./delete-item.component.css']
})
export class DeleteItemComponent implements OnInit {

  @Input() item:any;
  @Input() section:string = "";
  @Output() onCancel = new EventEmitter<any>();
  @Output() onDelete = new EventEmitter<any>();

  constructor(private portfolioService:PortfolioService) { }

  ngOnInit(): void {
  }

  deleteItem(item:any, section:string) {
    this.portfolioService.deleteItem(item,section).subscribe();
    this.onDelete.emit(item);
  }

  cancel(){
    this.onCancel.emit();
  }

}
