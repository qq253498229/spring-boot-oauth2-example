import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowUserListComponent } from './show-user-list.component';

describe('ShowUserListComponent', () => {
  let component: ShowUserListComponent;
  let fixture: ComponentFixture<ShowUserListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowUserListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowUserListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
